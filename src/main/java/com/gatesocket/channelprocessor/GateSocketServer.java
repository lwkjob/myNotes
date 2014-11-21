package com.gatesocket.channelprocessor;

import java.util.HashMap;

import com.gatesocket.channel.stream.SimpleStreamParser;
import com.gatesocket.channel.transport.socket.server.NioSocketChannelServerFactory;
/**
 * 
 * @Creator Fx
 * @Create-Date 2012-10-15
 */
public class GateSocketServer{

	public static void main(String args[])  throws Exception{
		
		HashMap hashMap = ParseXML.getXmlConfig();
		int port = Integer.parseInt((String) hashMap.get("serverport"));
		int poolsize = Integer.parseInt((String) hashMap.get("poolsize"));
		NioSocketChannelServerFactory niochannelserver = new NioSocketChannelServerFactory();
		niochannelserver.setPort(port);
		niochannelserver.setPoolSize(poolsize);
		niochannelserver.setStreamParser(new SimpleStreamParser());
		niochannelserver.afterPropertiesSet();
		niochannelserver.start();
	}
}

