package com.gatesocket.channel;

import java.io.IOException;

import com.gatesocket.Request;
import com.gatesocket.Response;

/**
 * Channel Executor Template
 * 
 * it is thread-safe,support any channel pool
 * 
 * @Creator Fx
 * @Create-Date 2012-5-7
 */
public class ChannelTemplate {


	private ChannelPool channelPool;

	/**
	 * default constructor
	 */
	public ChannelTemplate() {

	}

	/**
	 * 
	 * @param channelPool
	 */
	public ChannelTemplate(ChannelPool channelPool) {
		this.channelPool = channelPool;
	}

	/**
	 * Send Request to Channel for a Response
	 * 
	 * @param request
	 * @return
	 */
	public Response process(Request request) {
		Response response = new Response();
		this.process(request, response);
		return response;
	}
	

	/**
	 * Send Request,return content will be written into response
	 * 
	 * @param request
	 * @param response
	 */
	public void process(final Request request, final Response response) {
		this.execute(new ChannelCallback() {
			public Object execute(Channel channel) throws ChannelException, IOException {
				channel.process(request, response);
				return true;
			}
		});
	}

	/**
	 * Channel Callback Executor API
	 * 
	 * @param callback
	 * @return
	 */
	public Object execute(ChannelCallback callback) {
		Channel channel = channelPool.getChannel();
		try {
			if (!channel.isConnected()) {
				channel.connect();
			}
			return callback.execute(channel);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				channel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Inject a ChannelPool for ChannelTemplate
	 * 
	 * @param channelPool
	 */
	public void setChannelPool(ChannelPool channelPool) {
		this.channelPool = channelPool;
	}

}
