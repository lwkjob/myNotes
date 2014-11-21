package com.gatesocket.channel.transport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.gatesocket.channel.Channel;
import com.gatesocket.session.SessionContextListener;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-20
 */
public abstract class ChannelFactory {

	protected Channel channel;

	private String name;

	private List<SessionContextListener> listeners = new ArrayList<SessionContextListener>();

	public Object getObject() throws Exception {
		return this.channel;
	}

	public Class<?> getObjectType() {
		return channel == null ? Channel.class : channel.getClass();
	}

	public boolean isSingleton() {
		return true;
	}

//	public void afterPropertiesSet() throws Exception {
//		channel = newChannel();
//		channel.setName(name);
//
//		for (Iterator<SessionContextListener> itr = this.listeners.iterator(); itr.hasNext();) {
//			channel.addSessionContextListener(itr.next());
//		}
//	}

	public void setBeanName(String name) {
		this.name = name;
	}

	public void setListeners(List<SessionContextListener> listeners) {
		this.listeners = listeners;
	}

	protected abstract Channel newChannel();

}
