package com.gatesocket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-4
 */
public class Response implements Cloneable {

	/**
	 * remote has no response
	 */
	public static final String ERR_NO_RESP = "ERR_NO_RESP";

	/**
	 * time out
	 */
	public static final String ERR_TIME_OUT = "ERR_TIME_OUT";

	/**
	 * can not connect,network error
	 */
	public static final String ERR_NO_CONNECT = "ERR_NO_CONNECT";

	private Map<String, Object> attributes = new HashMap<String, Object>();

	private Map<String, String> headers = new HashMap<String, String>();

	private Object body;

	private String characterEncoding = "UTF-8";

	private String errorCode;

	private volatile boolean timeout = false;

	public Response() {

	}

	public Response(Map<String, String> headers) {
		this();
		this.headers.putAll(headers);
	}

	public boolean isTimeout() {
		return this.timeout;
	}

	public void setTimeout() {
		this.timeout = true;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public boolean isError() {
		return this.errorCode != null;
	}

	public Object getAttribute(String key) {
		return this.attributes.get(key);
	}

	public void setAttribute(String key, Object obj) {
		this.attributes.put(key, obj);
	}

	public void setHeader(String index, String value) {
		this.headers.put(index, value);
	}

	public String getHeader(String index) {
		return this.headers.get(index);
	}

	public Iterator<String> headerKeys() {
		return this.headers.keySet().iterator();
	}

	public Map<String, String> getHeaders() {
		return new HashMap<String, String>(this.headers);
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers.clear();
		this.headers.putAll(headers);
	}

	public Object getBody() {
		return this.body;
	}

	public void setBody(Object obj) {
		this.body = obj;
	}

	public String getCharacterEncoding() {
		return this.characterEncoding;
	}

	public void setCharacterEncoding(String encoding) {
		this.characterEncoding = encoding;
	}

	public void clear() {
		this.headers.clear();
		this.body = null;
	}

	public Response clone() throws CloneNotSupportedException {
		Response response = new Response();
		response.body = body;
		response.headers.putAll(this.headers);
		response.attributes.putAll(this.attributes);
		return response;
	}
}
