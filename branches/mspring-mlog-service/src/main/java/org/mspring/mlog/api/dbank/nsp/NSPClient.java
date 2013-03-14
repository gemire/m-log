package org.mspring.mlog.api.dbank.nsp;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.mspring.mlog.api.dbank.nsp.support.common.AbsNSPClient;
import org.mspring.mlog.api.dbank.nsp.support.common.CustomFilePart;
import org.mspring.mlog.api.dbank.nsp.support.common.NSPException;
import org.mspring.mlog.api.dbank.nsp.support.common.NSPResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.phprpc.util.PHPSerializer;

/**
 * dbank-sdk nsp.NSPClient<br/>
 * Desc:NSP Client<br/>
 * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a>
 * 2011 下午3:34:33
 */
public class NSPClient extends AbsNSPClient {

	private String id;
	private String secret;
	private boolean app = false;
	
	private static boolean corrected = false;
	private static long correctionTime = 0;
	
	/**
	 * NSP Client构造函数(可以作为app和user)
	 * @param id <a href="http://apps.dbank.com/developer/">申请的appId</a>或者
	 * 			 <a href="http://open.dbank.com/wikiNew/index.php?title=%E5%BC%80%E6%94%BE%E5%B9%B3%E5%8F%B0%E9%89%B4%E6%9D%83">登陆的sessionId</a>
	 * @param secret  与appId对应的<a href="http://open.dbank.com/appDatabase.html?v=2.5.8#3">APP Key</a>或者是在
	 * 			 <a href="http://open.dbank.com/wikiNew/index.php?title=Nsp.auth.createClient">createClient</a>时返回的clientId
	 */
	public NSPClient(String id, String secret) {
		this.id = id;
		this.secret = secret;
		this.app = StringUtils.isNumeric(id);
	}

	/**
	 * NSP Client构造函数(只针对app)
	 * @param id  <a href="http://apps.dbank.com/developer/">申请的appId</a>
	 * @param secret 与appId对应的<a href="http://open.dbank.com/appDatabase.html?v=2.5.8#3">APP Key</a>
	 */
	public NSPClient(int id, String secret) {
		this.id = String.valueOf(id);
		this.secret = secret;
		this.app = true;
	}

	/**
	 * 调用NSP平台服务公共方法
	 * @throws NSPException 
	 */
	public Object callService(String service, Object[] params) throws NSPException {

		Map<String, String> ps = new TreeMap<String, String>();
		if (this.app) {
			ps.put(NSP_APP, this.id);
		} else {
			ps.put(NSP_SID, this.id);
		}

		PHPSerializer phpSerializer = new PHPSerializer();
		try {
			ps.put(NSP_PARAMS, new String(phpSerializer.serialize(params)));
		} catch (Exception e) {
			throw new NSPException(2, "参数phprpc序列化异常");
		}
		ps.put(NSP_SVC, service);
		if (this.corrected) {
			ps.put(NSP_TS, String.valueOf((System.currentTimeMillis() + this.correctionTime) / 1000L));
		} else {
			ps.put(NSP_TS, String.valueOf(System.currentTimeMillis() / 1000L));
		}
		ps.put(NSP_FMT, "php-rpc");

		String data = getPostData(this.secret, ps);
		NSPResponse response = request(NSP_URL, data);
		if (response.getCode() == 109 && !corrected) {// 第一次出现
			SimpleDateFormat fmt = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
			Date serverTime = null;
			try {
				serverTime = fmt.parse(response.getHeader("Date"));
				this.correctionTime = (serverTime.getTime() - System.currentTimeMillis());
			} catch (ParseException e) {
			}
			this.corrected = true;
			ps.put(NSP_TS, String.valueOf((System.currentTimeMillis() + this.correctionTime) / 1000L));
			data = getPostData(this.secret, ps);
			response = request(NSP_URL, data);
		}
		if (response == null) {
			throw new NSPException(2, "Server No Response");
		}
		Object result = null;
		try {
			result = phpSerializer.unserialize(response.getContent());
		} catch (Exception e) {
			throw new NSPException(1, e.getMessage());
		}
		return result;
	}

	/**
	 * 上传文件
	 * @param <b>host</b> 服务器地址
	 * @param <b>tstr</b> 调用upauth返回的时间戳
	 * @param <b>file</b> 上传的文件对象(java.io.File)
	 * @return
	 * @throws NSPException 
	 */
	protected String uploadFile(String host, String tstr, File file) throws NSPException {
		Map<String, String> ps = new TreeMap<String, String>();
		if(this.app) {
			ps.put(NSP_APP, this.id);
		}else {
			ps.put(NSP_SID, this.id);
		}
		ps.put(NSP_TS, tstr);
		ps.put("nsp_fmt", "JSON");
		String requestData = getPostData(this.secret, ps);
		NSPResponse response = requestForUpload("http://" + host + "/upload/up.php", requestData, file);
		return new String(response.getContent());
	}

	/**
	 * 上传文件
	 * @param httpurl
	 * @param data
	 * @param file
	 * @return
	 * @throws NSPException
	 */
	private NSPResponse requestForUpload(String httpurl, String data, File file) throws NSPException {
		PostMethod filePost = new PostMethod(httpurl) {
			public String getRequestCharSet() {
				return "UTF-8";//
			}
		};
		filePost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		filePost.getParams().setParameter("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		client.getHttpConnectionManager().getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		client.getHttpConnectionManager().getParams().setParameter("Content-Type", "multipart/form-data");
		List<Part> parts = new ArrayList<Part>();
		try {
			// 通过以下方法可以模拟页面参数提交
			String[] nv = data.split("&");
			for (int i = 0; i < nv.length; i++) {
				String[] nvp = nv[i].split("=");
				if (nvp.length == 2) {
					parts.add(new StringPart(nvp[0], nvp[1]));
				}
			}
			parts.add(new CustomFilePart(file.getName(), file));
			Part[] partArr = parts.toArray(new Part[0]);
			filePost.setRequestEntity(new MultipartRequestEntity(partArr, filePost.getParams()));
			int status = client.executeMethod(filePost);
			NSPResponse response = new NSPResponse();
			response.setStatus(status);
			InputStream stream = filePost.getResponseBodyAsStream();
			response.setContent(IOUtils.toByteArray(stream));
			return response;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			filePost.releaseConnection();
		}
		return null;
	}

	/**
	 * 返回服务对象(目前支持 ： VFS.class  和 user.class)
	 * @param clz
	 * @return
	 */
	protected <T> T service(Class<T> clz) {
		if(clz.equals(VFS.class)) {
			return (T) new VFS(this);
		}else if(clz.equals(user.class)) {
			return (T) new user(this);
		}
		return null;
	}

}
