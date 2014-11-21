package com.gatesocket;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-05-4
 */
public class Request implements Cloneable {

	private Map<String, Object> attributes = new HashMap<String, Object>();

	private Map<String, String> headers = new HashMap<String, String>();

	private Object body;

	private String characterEncoding = "UTF-8";

	public Request() {

	}

	public Request(Object body) {
		this.body = body;
	}

	public Request(Object body, Map<String, String> headers) {
		this.headers.putAll(headers);
		this.body = body;
	}

	public Map<String, String> getHeaders() {
		return new HashMap<String, String>(this.headers);
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers.clear();
		this.headers.putAll(headers);
	}

	public void setHeader(String key, String value) {
		this.headers.put(key, value);
	}

	public Object getAttribute(String key) {
		return this.attributes.get(key);
	}

	public void setAttribute(String key, Object obj) {
		this.attributes.put(key, obj);
	}

	public String getHeader(String index) {
		return this.headers.get(index);
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

	public Request clone() throws CloneNotSupportedException {
		Request request = new Request();
		request.body = this.body;
		request.headers.putAll(this.headers);
		request.attributes.putAll(this.attributes);
		return request;
	}
}
