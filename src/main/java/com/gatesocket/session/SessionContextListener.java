package com.gatesocket.session;

import com.gatesocket.session.SessionContextException;
import com.gatesocket.session.SessionEvent;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-13
 */
public interface SessionContextListener {

	void onSessionCreate(SessionEvent evt) throws SessionContextException;

	void onSessionDestroy(SessionEvent evt) throws SessionContextException;
}
