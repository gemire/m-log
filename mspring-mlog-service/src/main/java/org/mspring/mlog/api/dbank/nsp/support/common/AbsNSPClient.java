package org.mspring.mlog.api.dbank.nsp.support.common;

import static java.net.URLEncoder.encode;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;
import org.phprpc.util.PHPSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbsNSPClient {

	private static final Logger LOG = LoggerFactory.getLogger(AbsNSPClient.class);
	
    protected static final String NSP_APP = "nsp_app";
    protected static final String NSP_SID = "nsp_sid";
    protected static final String NSP_CLIENT = "client";
    protected static final String NSP_KEY = "nsp_key";
    protected static final String NSP_SVC = "nsp_svc";
    protected static final String NSP_TS = "nsp_ts";
    protected static final String NSP_STATUS = "NSP_STATUS";
    protected static final String NSP_PARAMS = "nsp_params";
    protected static final String NSP_FMT = "nsp_fmt";

    protected static String NSP_URL = "http://api.dbank.com/rest.php";

    protected String getPostData(String secret, Map<String, String> params) {

        StringBuffer data = new StringBuffer();
        StringBuffer base = new StringBuffer(secret);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String k = entry.getKey();
            if (NSP_KEY.equals(k))
                continue;
            String v = entry.getValue();
            try {
                data.append(encode(k, "UTF-8")).append('=')
                        .append(encode(v, "UTF-8")).append('&');
            } catch (UnsupportedEncodingException e) {
            }
            base.append(k).append(v);
        }
        try {
        	String key = new MD5(base.toString().getBytes("UTF-8")).asHex().toUpperCase();
            data.append(NSP_KEY).append('=').append(key);
        }catch(UnsupportedEncodingException e) {// never get exception
        }
        return data.toString();
    }

    protected NSPResponse request(String httpurl, String data) throws NSPException {
    	return request(httpurl, data, null);
    }

    protected NSPResponse request(String httpurl, String data,
            Map<String, String> headers) throws NSPException {
    	if(LOG.isDebugEnabled()) {
    		LOG.debug("rquest:"+httpurl+"?"+data);
    	}
        URL url = null;
        HttpURLConnection conn = null;
		try {
			url = new URL(httpurl);
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e1) {
			throw new NSPException(2,"服务临时不可用");
		}
        HttpURLConnection.setFollowRedirects(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("accept-encoding", "gzip");
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet())
                conn.setRequestProperty(entry.getKey(), entry.getValue());
        }

        if (data != null) {
            OutputStreamWriter wr;
			try {
				wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
				wr.write(data);
	            wr.flush();
	            wr.close();
			} catch (IOException e) {
				throw new NSPException(2,"服务临时不可用");
			}
        }

        boolean isGzip = false;
        NSPResponse response = new NSPResponse();
        for (int i = 0;; i++) {
            String k = conn.getHeaderFieldKey(i);
            String v = conn.getHeaderField(i);
            if (k == null && v == null)
                break;

            if (k == null) {
                // HTTP Status
                response.setStatus(Integer.parseInt(v.split("\\s+")[1]));
            } else if (NSP_STATUS.equalsIgnoreCase(k)) {
                // NSP Status
                response.setCode(Integer.parseInt(v));
            } else {
                // Normal Header
                response.putHeader(k, v);
            }
            if(k != null && k.equalsIgnoreCase("Content-Encoding") && v != null &&  v.equalsIgnoreCase("gzip")) {
            	isGzip = true;
            }
        }
        try {
        	if (response.getStatus() == 200 || response.getStatus() == 302) {
            	if(isGzip) {
            		response.setContent(IOUtils.toByteArray(new GZIPInputStream(conn.getInputStream())));
            	}else {
            		response.setContent(IOUtils.toByteArray(conn.getInputStream()));
            	}
            }
        }catch(IOException ioe) {
        	throw new NSPException(2,"服务临时不可用");
        }

        if (response.getCode() > 0) {
        	PHPSerializer phpSerializer = new PHPSerializer();
        	Map<String, Object> map = null;
			try {
				map = NSPWrapper.toBean(phpSerializer.unserialize(response.getContent()), Map.class);
			} catch (Exception e) {
				throw new NSPException(2,"返回数据phprpc反序列化错误");
			}
            if (map == null)
                throw new NSPException(response.getCode(), "Unknown error");
            else if (map.containsKey("error")) {
            	String errMsg = "";
            	if(map.get("error") instanceof String) {
            		errMsg = (String) map.get("error");
            	}else if(map.get("error") instanceof byte[]) {
            		errMsg = new String((byte[]) map.get("error"));
            	}
            }
        }
        if(LOG.isDebugEnabled()) {
    		LOG.debug("response:"+new String(response.getContent()));
    	}
        return response;
    }

    protected abstract Object callService(String service, Object... params) throws NSPException, IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;

    public void setNspUrl(String url) {
        NSP_URL = url;
    }

}
