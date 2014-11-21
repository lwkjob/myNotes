package com.gatesocket.channel.transport.socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.transport.socket.DefaultSocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.gatesocket.utils.LifecycleException;
import com.gatesocket.channel.AbstractChannelServer;
import com.gatesocket.channel.stream.StreamParser;
import com.gatesocket.channelprocessor.EbcChannelProcessor;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-14
 */
public class NioSocketEbcChannelServer extends AbstractChannelServer {


	private NioSocketAcceptor acceptor;

	private int backLog = 5;

	private volatile boolean state = false;

	private int port;

	private List<IoFilter> ioFilters = new ArrayList<IoFilter>();

	private StreamParser streamParser;

	private int bufferSize = 8172;

	public void setPort(int port) {
		this.port = port;
	}

	public void setBackLog(int backLog) {
		this.backLog = backLog;
	}

	public void setStreamParser(StreamParser streamParser) {
		this.streamParser = streamParser;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public synchronized void start() throws LifecycleException {

			System.out.println("starting nio socket server ...");
			if (state) {
				System.out.println("nio socket server has been started,this operation is ignored!!");
				return;
			}
			System.out.println("nio socket server process couter:" + this.poolSize);
			this.acceptor = new NioSocketAcceptor(this.poolSize);
			System.out.println("nio socket server backlog size:" + this.backLog);
			this.acceptor.setBacklog(this.backLog);

			DefaultSocketSessionConfig config = (DefaultSocketSessionConfig) this.acceptor.getSessionConfig();
			config.setReuseAddress(true);
			config.setTcpNoDelay(true);
			
//			config.setKeepAlive(true);
			//config.setBothIdleTime(idleTime)
		
			System.out.println("nio socket server receive buffer size:" + this.bufferSize);
			config.setReceiveBufferSize(bufferSize);

			NioSocketServerHandler handler = new NioSocketServerHandler();
			handler.setStreamParser(streamParser);
			handler.setFilters(filters);
			handler.setChannelProcessor(new EbcChannelProcessor());  //已经更改
			handler.setSessionEventPublisher(sessionEventPublisher);
			handler.setCharset(charset);

			this.acceptor.setHandler(handler);

			for (int i = 0; i < this.ioFilters.size(); i++) {
				String key = "last_" + i;
				this.acceptor.getFilterChain().addLast(key, ioFilters.get(i));
			}

			try {
				System.out.println("nio socket server bind port:" + this.port);
				this.acceptor.bind(new InetSocketAddress(this.port));
			} catch (IOException e) {
				System.out.println("nio socket server start failed!!");
				System.out.println(e.getMessage());
				throw new LifecycleException(e);
			}
			this.state = true;
			System.out.println("nio socket server started successfully:");
			System.out.println("-->BIND PORT:" + this.port);
			System.out.println("-->PROC SIZE:" + this.poolSize);
			System.out.println("-->BACK LOG :" + this.backLog);
		
	}

	public synchronized void stop() throws LifecycleException {
			System.out.println("stopping nio socket server ...");
			if (!state) {
				System.out.println("nio socket server has been stopped,this operation is ignored!!");
				return;
			}
			this.acceptor.unbind();
			this.acceptor.dispose(true);
			this.state = false;
			System.out.println("nio socket server stopped successfully:");
			System.out.println("-->BIND PORT:" + this.port);
			System.out.println("-->PROC SIZE:" + this.poolSize);
			System.out.println("-->BACK LOG :" + this.backLog);
		
	}

	public boolean isRunning() {
		return this.state;
	}

	public void setIoFilters(List<IoFilter> filters) {
		this.ioFilters = filters;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("nio socket channel server\r\n");
		buffer.append("-->bind port:" + this.port + "\r\n");
		buffer.append("-->pool size:" + this.poolSize + "\r\n");
		buffer.append("-->back log :" + this.backLog + "\r\n");
		buffer.append("-->state    :" + this.isRunning() + "\r\n");
		return buffer.toString();
	}

}
