package com.gatesocket.channel;

import java.io.IOException;

/**
 * 
 * @Creator Fx
 * @Create-Date 2011-5-8
 */
public interface ChannelCallback {

	Object execute(Channel channel) throws ChannelException, IOException;
}
