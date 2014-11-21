package com.gatesocket.channel.transport.socket.pool;

import com.gatesocket.channel.Channel;
import com.gatesocket.channel.stream.StreamParser;
import com.gatesocket.channel.transport.SimpleChannelPool;
import com.gatesocket.channel.transport.socket.client.SocketSenderChannel;

/**
 * 
 * @Creator Fx
 * @Create-Date 2013-3-15
 */
public class SimpleSocketSenderChannelPool extends SimpleChannelPool {

	protected StreamParser streamParser;

	private String remoteAddress;

	private int port;

	@Override
	protected Channel newChannel() {
		SocketSenderChannel cc = new SocketSenderChannel();
		cc.setPort(this.port);
		cc.setRemoteAddress(remoteAddress);
		cc.setStreamParser(streamParser);
		return cc;
	}

	public void setStreamParser(StreamParser streamParser) {
		this.streamParser = streamParser;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Type:SimpleSocketSenderChannelPool\r\n");
		buffer.append("--->remote address:" + this.remoteAddress + "\r\n");
		buffer.append("--->remote port   :" + this.port + "\r\n");
		return buffer.toString();
	}

}
