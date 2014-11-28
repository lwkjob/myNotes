package com.capinfo.payment.ebcross;

import com.gatesocket.Request;
import com.gatesocket.Response;
import com.gatesocket.channel.ChannelTemplate;
import com.gatesocket.channel.stream.SimpleStreamParser;
import com.gatesocket.channel.transport.socket.pool.SimpleSocketSenderChannelPool;

/**
 * SOCKET通讯
 * 
 * @author 徐飞
 */
public class SocketRequest {
	protected static SimpleSocketSenderChannelPool kachannel;
	protected static ChannelTemplate channelTemplate;
	/**
	 * 
	 * 发送报文
	 * 
	 * 
	 */
	public static void sendRequest(String data) throws Exception {
		if(null==kachannel ){
			System.out.println("prepare to init kachannel!");
			kachannel = new SimpleSocketSenderChannelPool();
		    kachannel.setPort(EbcParameter.port);
		    kachannel.setRemoteAddress(EbcParameter.ServerIp);
		    kachannel.setStreamParser(new SimpleStreamParser());
		    kachannel.init();
		}
		if(null==channelTemplate ){
		    channelTemplate = new ChannelTemplate(kachannel);
		}
		Request request = new Request();
		Response response = null;
		request.setBody(data.getBytes());
		channelTemplate.process(request,response);
	}



	public static void main(String[] args) {
	}
}
