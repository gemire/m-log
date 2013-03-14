package org.mspring.mlog.api.dbank.nsp.support.common;

import java.util.HashMap;
import java.util.Map;

public class NSPResponse {

	/**
	 * HTTP连接的返回结果
	 */
	private int status = 200;

	/**
	 * NSP_STATUS
	 */
	private int code = 0;

	/**
	 * 返回的header
	 */
	private Map<String, String> headers;

	/**
	 * 返回的内容
	 */
	private byte[] content;

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void putHeader(String key, String value) {
		if (headers == null)
			headers = new HashMap<String, String>(4);
		headers.put(key, value);
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public boolean containsHeader(String key) {
		return headers != null && headers.containsKey(key);
	}

	public String getHeader(String key) {
		return headers == null ? null : headers.get(key);
	}

}
