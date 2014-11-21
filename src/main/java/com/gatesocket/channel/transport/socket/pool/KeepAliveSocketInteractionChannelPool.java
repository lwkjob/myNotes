package com.gatesocket.channel.transport.socket.pool;

import com.gatesocket.channel.Channel;
import com.gatesocket.channel.stream.StreamParser;
import com.gatesocket.channel.transport.KeepAliveChannelPool;
import com.gatesocket.channel.transport.socket.client.SocketInteractionChannel;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-18
 */
public class KeepAliveSocketInteractionChannelPool extends KeepAliveChannelPool {

	protected StreamParser streamParser;

	private String remoteAddress;

	private int port;

	private int timeout = 30 * 1000;

	@Override
	protected Channel newChannel() {
		SocketInteractionChannel cc = new SocketInteractionChannel();
		cc.setPort(this.port);
		cc.setRemoteAddress(remoteAddress);
		cc.setStreamParser(streamParser);
		cc.setTimeout(timeout);
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

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Type:KeepAliveSocketInteractionChannelPool\r\n");
		buffer.append("--->remote address:" + this.remoteAddress + "\r\n");
		buffer.append("--->remote port   :" + this.port + "\r\n");
		buffer.append("--->pool size     :" + this.poolSize + "\r\n");
		buffer.append("--->alive size    :" + this.number + "\r\n");
		return buffer.toString();
	}

}
