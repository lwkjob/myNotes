package com.gatesocket.session;


/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-14
 */
public interface Session {

	Object getAttachment();

	void setAttachment(Object obj);

	String getId();

	long getCreateTime();

	void close();

	boolean isClosed();

	Object getAttribute(String key);

	void setAttribute(String key, Object value);

	//
}
