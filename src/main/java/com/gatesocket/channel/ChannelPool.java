package com.gatesocket.channel;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-8
 */
public interface ChannelPool {

	/**
	 * must called after channel pool created
	 */
	public void init();

	/**
	 * must called after channel pool destroyed
	 */
	public void destroy();

	/**
	 * set channel pool size
	 * 
	 * @param poolSize
	 */
	public void setPoolSize(int poolSize);

	/**
	 * create a new channel for communication
	 * 
	 * @return
	 */
	public Channel getChannel();

}
