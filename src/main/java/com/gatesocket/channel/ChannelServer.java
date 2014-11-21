package com.gatesocket.channel;

import com.gatesocket.utils.Lifecycle;
import com.gatesocket.channel.filter.Filter;
import com.gatesocket.channel.ChannelProcessor;
import com.gatesocket.session.SessionContextListener;

/**
 * Channel Server,this is request receiver and response sender
 * 
 * @Creator Fx
 * @Create-Date 2012-5-13
 */
public interface ChannelServer extends Lifecycle {

	/**
	 * channel server
	 * 
	 * @return
	 */
	String getName();

	/**
	 * set channel server
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * thread pool size of channel server
	 * 
	 * @param poolsize
	 */
	void setPoolSize(int poolsize);

	/**
	 * 
	 * @return
	 */
	int getPoolSize();

	/**
	 * Inject Channel Processor for this channel server
	 * 
	 * User can implements Channel Processor to process transaction
	 * 
	 * @param process
	 */
	void setChannelProcessor(ChannelProcessor process);

	/**
	 * Add a filter into channel server
	 * 
	 * Filter will process transaction
	 * 
	 * @param filter
	 */
	void addFilter(Filter filter);

	/**
	 * Add a Session Listener
	 * 
	 * @param listener
	 */
	void addSessionContextListener(SessionContextListener listener);
}
