package com.gatesocket.transport.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;



import com.gatesocket.channel.AbstractChannel;
import com.gatesocket.channel.ChannelException;
import com.gatesocket.channel.stream.StreamParser;
import com.gatesocket.session.SocketSession;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-4
 */
public abstract class SocketChannel extends AbstractChannel {


	protected StreamParser streamParser;

	protected String remoteAddress;

	protected int port;

	protected Socket socket = null;

	protected SocketSession session;

	protected int timeout = 30 * 1000;

	public void close() throws ChannelException, IOException {
		if (!this.isConnected()) {
			System.out.println("socket client connection has not connected,this operation is ignored!!");
			return;
		}
		System.out.println("Disconnect socket client connection ...");
		System.out.println("-->CONN IP  :" + this.remoteAddress);
		System.out.println("-->CONN PORT:" + this.port);
		session.close();
		System.out.println("Socket connection disconnected sucessfully!!");
	}

	public void connect() throws ChannelException, IOException {
		if (this.isConnected()) {
			System.out.println("socket client connection has connected,this operation is ignored!!");
			return;
		}

		System.out.println("Establish socket client connection ...");
		System.out.println("-->CONN IP  :" + this.remoteAddress);
		System.out.println("-->CONN PORT:" + this.port);
		socket = new Socket();
		InetSocketAddress sa = new InetSocketAddress(this.remoteAddress, this.port);
		socket.setSoTimeout(timeout);
		socket.connect(sa);
		socket.setKeepAlive(true);
		System.out.println("Establish socket session to remote server");
//		session = new SocketSession(this.socket, this.sessionEventPublisher);
		System.out.println("socket connection established sucessfully!!");

	}

	public boolean isConnected() {
		return socket != null && !socket.isClosed() && socket.isConnected() && socket.isBound();
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
}
