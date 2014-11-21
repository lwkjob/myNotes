package com.gatesocket.session;

import com.gatesocket.session.Session;
import com.gatesocket.session.SessionEvent;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-13
 */
public class SessionCreatedEvent implements SessionEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1058639162500355876L;

	private Session session;

	public SessionCreatedEvent(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return this.session;
	}

}

