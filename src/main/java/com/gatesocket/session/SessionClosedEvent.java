package com.gatesocket.session;

import com.gatesocket.session.Session;
import com.gatesocket.session.SessionEvent;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-14
 */
public class SessionClosedEvent implements SessionEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4421485151890994155L;

	public Session session;

	public SessionClosedEvent(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

}
