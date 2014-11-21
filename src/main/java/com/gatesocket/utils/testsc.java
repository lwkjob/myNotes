package com.gatesocket.utils;

import com.gatesocket.Request;
import com.gatesocket.Response;
import com.gatesocket.channel.ChannelTemplate;
import com.gatesocket.channel.stream.ProxySimpleStreamParser;
import com.gatesocket.channel.stream.SimpleStreamParser;
import com.gatesocket.channel.stream.StreamParser;
import com.gatesocket.channel.stream.configurable.ConfigurableStreamParser;
import com.gatesocket.channel.transport.KeepAliveChannelPool;
import com.gatesocket.channel.transport.socket.pool.KeepAliveSocketInteractionChannelPool;
//import com.gatesocket.channel.stream.simple.SimpleHeader;
import com.gatesocket.channel.transport.socket.server.NioSocketChannelServerFactory;

public class testsc {
	

	
	public static void main(String args[]) throws Exception{
//		int i=0;
//		i=i+1;
//		System.out.println(i);
//		KeepAliveSocketInteractionChannelPool kachannel = new KeepAliveSocketInteractionChannelPool();
//		kachannel.setPort(9008);
//		kachannel.setRemoteAddress("127.0.0.1");
//		kachannel.setStreamParser(new SimpleStreamParser());
//		kachannel.init();
//		System.out.println(++i);
//		ChannelTemplate channelTemplate = new ChannelTemplate(kachannel);
//		System.out.println(++i);
//		Request request = new Request();
//		Request request1 = new Request();
//
//		Request request2 = new Request();
//		Response response = null;
//		request.setBody("111222333".getBytes());
//		request1.setBody("sssssaaaa".getBytes());
//		request2.setBody("bbbbbcccc".getBytes());
//		System.out.println(++i);
//		try{
//			channelTemplate.process(request,response);
//			channelTemplate.process(request1,response);
//			channelTemplate.process(request2,response);
//		}
//		catch (Throwable e) {
//			System.out.println(e.getMessage());
//		} 
		NioSocketChannelServerFactory niochannelserver = new NioSocketChannelServerFactory();
		niochannelserver.setPort(9009);
		niochannelserver.setPoolSize(10);
		niochannelserver.setStreamParser(new SimpleStreamParser());
		niochannelserver.afterPropertiesSet();
		niochannelserver.start();
	}

}
