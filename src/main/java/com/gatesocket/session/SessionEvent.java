package com.gatesocket.session;

import java.io.Serializable;

/**
 * Session Event
 * 
 * @see SessionCreatedEvent
 * @ses SessionClosedEvent
 * 
 * @Creator Fx
 * @Create-Date 2012-5-12
 */
public interface SessionEvent extends Serializable {
	
	/**
	 * Get Session from Session Event
	 * 
	 * @return
	 */
	public Session getSession();
}
