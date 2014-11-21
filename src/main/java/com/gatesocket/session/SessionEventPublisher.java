package com.gatesocket.session;

import com.gatesocket.session.SessionContextException;
import com.gatesocket.session.SessionContextListener;
import com.gatesocket.session.SessionEvent;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-12
 */
public interface SessionEventPublisher {

	void publishSessionEvent(SessionEvent event) throws SessionContextException;

	void addListener(SessionContextListener listener);

	void removeListener(SessionContextListener listener);
}