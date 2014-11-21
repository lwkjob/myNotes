package com.gatesocket;

import com.gatesocket.session.Session;

/**
 * 
 * @Creator haitingr
 * @Create-Date 2010-12-1
 */
public interface HeaderCallback {

	void callback(Header header, Session session);
}
