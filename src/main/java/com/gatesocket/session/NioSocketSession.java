package com.gatesocket.session;

import org.apache.mina.core.session.IoSession;

import com.gatesocket.session.Session;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-14
 */
public class NioSocketSession implements Session {

	private IoSession session;
	
	private boolean flushedClose=false;
	
	public NioSocketSession(IoSession session) {
		this.session = session;
	}

	public void close() {
		this.session.close(true);
	}

	public Object getAttachment() {
		return session.getAttribute("attachment");
	}

	public long getCreateTime() {
		return session.getCreationTime();
	}

	public String getId() {
		return session.getId() + "";
	}

	public boolean isClosed() {
		return !session.isConnected();
	}

	public void setAttachment(Object obj) {
		session.setAttribute("attachment", obj);
	}
	
	public IoSession getSession(){
		return this.session;
	}

	public Object getAttribute(String key) {
		return this.session.getAttribute(key);
	}

	public void setAttribute(String key, Object value) {
		this.session.setAttribute(key, value);
	}
	
	public IoSession getNativeSession(){
		return this.session;
	}
	
	public void setFlushedClose(boolean value){
		this.flushedClose=value;
	}
	
	public boolean isFlushdedClose(){
		return this.flushedClose;
	}

}
