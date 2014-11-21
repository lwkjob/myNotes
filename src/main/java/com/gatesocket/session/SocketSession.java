package com.gatesocket.session;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.gatesocket.session.Session;
import com.gatesocket.SessionEventPublisher;
//import com.gatesocket.impl.SessionClosedEvent;
//import com.gatesocket.impl.SessionCreatedEvent;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-7
 */
public class SocketSession implements Session {

	private String id = UUID.randomUUID().toString();

	private Socket socket;

	private SessionEventPublisher sessionEventPublisher;

	private long createTime = System.currentTimeMillis();

	private Object attachement;

	private Map<String, Object> attributes = new HashMap<String, Object>();

	public SocketSession(Socket socket, SessionEventPublisher publisher) {
		this.socket = socket;
		this.sessionEventPublisher = publisher;
//		this.sessionEventPublisher.publishSessionEvent(new SessionCreatedEvent(this));
	}
 
	public void close() {
		try {
			this.socket.close();
//			this.sessionEventPublisher.publishSessionEvent(new SessionClosedEvent(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object getAttachment() {
		return this.attachement;
	}

	public Object getAttribute(String key) {
		return this.attributes.get(key);
	}

	public long getCreateTime() {
		return this.createTime;
	}

	public String getId() {
		return this.id;
	}

	public boolean isClosed() {
		return this.socket.isClosed();
	}

	public void setAttachment(Object obj) {
		this.attachement = obj;
	}

	public void setAttribute(String key, Object value) {
		this.attributes.put(key, value);
	}

	public Socket getSocket() {
		return this.socket;
	}

}

