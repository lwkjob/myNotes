package com.gatesocket.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.gatesocket.session.SessionContextException;
import com.gatesocket.session.SessionContextListener;
import com.gatesocket.session.SessionEvent;
import com.gatesocket.session.SessionEventPublisher;

/**
 * 
 * @Creator Fx
 * @Create-Date 2010-5-14
 */
public class DefaultSessionEventPublisher implements SessionEventPublisher {


	private List<SessionContextListener> listeners = new ArrayList<SessionContextListener>();

	public void addListener(SessionContextListener listener) {
		this.listeners.add(listener);
	}

	public void publishSessionEvent(SessionEvent event) throws SessionContextException {
		if (event instanceof SessionCreatedEvent) {
			for (Iterator<SessionContextListener> itr = listeners.iterator(); itr.hasNext();) {
				itr.next().onSessionCreate(event);
			}
		} else if (event instanceof SessionClosedEvent) {
			for (Iterator<SessionContextListener> itr = listeners.iterator(); itr.hasNext();) {
				itr.next().onSessionDestroy(event);
			}
		} else {
			System.out.println("unkown session event has occured,session event class is :" + event.getClass());
		}
	}

	public void removeListener(SessionContextListener listener) {
		listeners.remove(listener);
	}

}
