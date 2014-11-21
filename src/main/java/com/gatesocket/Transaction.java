package com.gatesocket;

import com.gatesocket.session.Session;

import com.gatesocket.utils.UUID;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-8
 */
public class Transaction implements Cloneable {

	public static final int ACTIVE = 1;

	public static final int TIMEOUT = 2;

	public static final int DEAD = 4;

	private String id;

	private Request request;

	private Response response;

	private Session session;

	private long createTime = System.currentTimeMillis();

	private int state = ACTIVE;


	public Transaction() {
		this.id = new UUID().toString();
	}

	protected Transaction(Session session) {
		this();
		this.session = session;
	}

	public Transaction(Request request, Response response, Session session) {
		this(session);
		this.request = request;
		this.response = response;
	}

	protected Transaction(String id, Request request, Response response, Session session) {
		this(request, response, session);
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public Response getResponse() {
		return response;
	}

	public String getId() {
		return id;
	}

	public Session getSession() {
		return session;
	}

	public long getCreateTime() {
		return this.createTime;
	}

	public boolean isActive() {
		return this.state == ACTIVE;
	}

	public boolean isDead() {
		return this.state == DEAD;
	}

	public void setDead() {
		this.state = DEAD;
	}

	public boolean isTimeout() {
		return this.state == TIMEOUT;
	}

	public void setTimeout() {
		this.state = TIMEOUT;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Transaction clone() throws CloneNotSupportedException {
		return new Transaction(this.id, this.request.clone(), this.response.clone(), this.session);
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("transaction[" + this.id + "]");
		return buffer.toString();
	}

}
