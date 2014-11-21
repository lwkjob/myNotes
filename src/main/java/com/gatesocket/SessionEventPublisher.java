package com.gatesocket;

import com.gatesocket.session.SessionContextException;
import com.gatesocket.session.SessionContextListener;
import com.gatesocket.session.SessionEvent;

/**
 * 
 * @Creator haitingr
 * @Create-Date 2010-12-13
 */
public interface SessionEventPublisher {

	void publishSessionEvent(SessionEvent event) throws SessionContextException;

	void addListener(SessionContextListener listener);

	void removeListener(SessionContextListener listener);
}