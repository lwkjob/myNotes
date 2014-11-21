package com.gatesocket.channel.transport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import org.springframework.beans.factory.BeanNameAware;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.beans.factory.InitializingBean;

import com.gatesocket.channel.ChannelServer;
import com.gatesocket.channel.filter.Filter;
import com.gatesocket.channel.ChannelProcessor;
import com.gatesocket.session.SessionContextListener;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-14
 */
public abstract class ChannelServerFactory {

	protected String name;

	protected List<SessionContextListener> listeners = new ArrayList<SessionContextListener>();

	protected int poolSize = 5;

	protected ChannelProcessor channelProcessor;

	protected List<Filter> filters = new ArrayList<Filter>();

	protected ChannelServer server = null;

	protected String charset = "UTF-8";

	public void setListeners(List<SessionContextListener> listeners) {
		this.listeners.addAll(listeners);
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public void setChannelProcessor(ChannelProcessor channelProcessor) {
		this.channelProcessor = channelProcessor;
	}

	public void setFilters(List<Filter> filters) {
		this.filters.addAll(filters);
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Class<?> getObjectType() {
		return ChannelServer.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public void setBeanName(String name) {
		this.name = name;
	}

	public void afterPropertiesSet() throws Exception {
		server = this.newChannelServer();
		server.setName(this.name);
		server.setPoolSize(this.poolSize);
		server.setChannelProcessor(this.channelProcessor);

		for (Iterator<Filter> itr = this.filters.iterator(); itr.hasNext();) {
			server.addFilter(itr.next());
		}

		for (Iterator<SessionContextListener> itr = this.listeners.iterator(); itr.hasNext();) {
			server.addSessionContextListener(itr.next());
		}

	}

	public Object getObject() throws Exception {
		return server;
	}

	public void start() {
		this.server.start();
	}

	public void stop() {
		this.server.stop();
	}

	protected abstract ChannelServer newChannelServer();

}
