package com.gatesocket.channel;

import java.util.ArrayList;
import java.util.List;

import com.gatesocket.channel.filter.Filter;
import com.gatesocket.channel.ChannelProcessor;
import com.gatesocket.session.SessionContextListener;
import com.gatesocket.session.SessionEventPublisher;
import com.gatesocket.session.DefaultSessionEventPublisher;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-8
 */
public abstract class AbstractChannelServer implements ChannelServer {

	protected String name;

	protected ChannelProcessor channelProcessor;

	protected List<Filter> filters = new ArrayList<Filter>();

	protected SessionEventPublisher sessionEventPublisher = new DefaultSessionEventPublisher();

	protected String charset = "UTF-8";

	protected int poolSize = 5;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public void setChannelProcessor(ChannelProcessor channelProcessor) {
		this.channelProcessor = channelProcessor;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public int getPoolSize() {
		return this.poolSize;
	}

	public void addFilter(Filter filter) {
		this.filters.add(filter);
	}

	public void addSessionContextListener(SessionContextListener listener) {
		this.sessionEventPublisher.addListener(listener);
	}

}
