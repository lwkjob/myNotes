package com.gatesocket.channel;

import com.gatesocket.Transaction;
import com.gatesocket.channel.ChannelException;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-13
 */
public interface ChannelProcessor {

	/**
	 * 
	 * @param request
	 * @param response
	 * @param context
	 * @throws ChannelException
	 */
	public void process(Transaction transaction) throws ChannelException;
}
