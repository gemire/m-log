/**
 * 
 */
package org.mspring.mlog.api.teamtoy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gao Youbo
 * @since 2013-3-14
 * @description
 * @TODO
 */
public class TeamtoyAPI {
    public final static int BUFFER_SIZE = 4048;
    private static Map<String, String> params;

    /**
     * 
     */
    public TeamtoyAPI() {
        // TODO Auto-generated constructor stub
    }

    public void addParam(String key, String value) {
        if (params == null) {
            params = new HashMap<String, String>();
        }
        params.put(key, value);
    }

    public String request() {
        String url = Const.URL;
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                url += "&" + entry.getKey() + "=" + entry.getValue();
            }
        }
        HttpURLConnection con = getConnectionFromUrl(url, "GET");
        String result = null;
        try {
            result = getStringDataFromConnection(con);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (con != null)
            con.disconnect();

        return result;
    }

    /**
     * 创建Http连接
     * 
     * @param baseurl
     * @param method
     * @return
     */
    private static HttpURLConnection getConnectionFromUrl(String baseurl, String method) {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(baseurl);
        } catch (MalformedURLException e) {
            // never come here
            e.printStackTrace();
        }

        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            con.setRequestMethod(method);
        } catch (ProtocolException e1) {
            // never come here
            if (con != null) {
                con.disconnect();
                con = null;
            }
        }
        return con;
    }

    /**
     * 从HttpURLConnection中获取返回数据
     * 
     * @param con
     * @return
     * @throws IOException
     */
    private static String getStringDataFromConnection(HttpURLConnection con) throws IOException {
        InputStream is = null;
        try {
            is = con.getInputStream();
        } catch (IOException e) {
            is = con.getErrorStream();
        }

        try {
            return stream2String(is);
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                }
        }
    }

    /**
     * 从文件流中读取字符串
     * 
     * @param is
     * @return
     * @throws IOException
     */
    private static String stream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[BUFFER_SIZE];
        int len = 0;
        try {
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
        } catch (IOException e) {
            //
            throw new IOException(e);
        }

        String result = null;
        try {
            result = new String(baos.toByteArray(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            result = new String(baos.toByteArray());
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
            }
        }
        return result;
    }
}
