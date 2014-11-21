package com.gatesocket.channel.transport.socket.pool;

import com.gatesocket.channel.Channel;
import com.gatesocket.channel.stream.StreamParser;
import com.gatesocket.channel.transport.KeepAliveChannelPool;
import com.gatesocket.channel.transport.socket.client.SocketSenderChannel;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-18
 */
public class KeepAliveSocketSenderChannelPool extends KeepAliveChannelPool {

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
		buffer.append("Type:KeepAliveSocketSenderChannelPool\r\n");
		buffer.append("--->remote address:" + this.remoteAddress + "\r\n");
		buffer.append("--->remote port   :" + this.port + "\r\n");
		buffer.append("--->pool size     :" + this.poolSize + "\r\n");
		buffer.append("--->alive size    :" + this.number + "\r\n");
		return buffer.toString();
	}

}
