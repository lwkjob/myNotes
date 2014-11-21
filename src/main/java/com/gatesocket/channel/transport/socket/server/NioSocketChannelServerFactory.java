package com.gatesocket.channel.transport.socket.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.filterchain.IoFilter;

import com.gatesocket.channel.ChannelServer;
import com.gatesocket.channel.stream.StreamParser;
import com.gatesocket.channel.transport.ChannelServerFactory;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-14
 */
public class NioSocketChannelServerFactory extends ChannelServerFactory {

	private int backLog = 10;

	private int bufferSize = 8172;

	private List<IoFilter> ioFilters = new ArrayList<IoFilter>();

	private int port = 8001;

	private StreamParser streamParser;

	@Override
	protected ChannelServer newChannelServer() {
		NioSocketChannelServer channelServer = new NioSocketChannelServer();
		channelServer.setBackLog(backLog);
		channelServer.setBufferSize(bufferSize);
		channelServer.setPort(port);
		channelServer.setIoFilters(this.ioFilters);
		channelServer.setStreamParser(streamParser);
		channelServer.setCharset(this.charset);
		return channelServer;
	}

	public void setBackLog(int backLog) {
		this.backLog = backLog;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public void setIoFilters(List<IoFilter> ioFilters) {
		this.ioFilters = ioFilters;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setStreamParser(StreamParser streamParser) {
		this.streamParser = streamParser;
	}

}
