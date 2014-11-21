package com.gatesocket.channel.transport.socket.client;

import java.io.IOException;

import com.gatesocket.Request;
import com.gatesocket.Response;
import com.gatesocket.channel.ChannelException;
import com.gatesocket.channel.stream.RequestHeader;
import com.gatesocket.channel.stream.Stream;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-9
 */
public class SocketSenderChannel extends SocketChannel {

	public void process(Request request, Response response) throws ChannelException, IOException {
		RequestHeader header = this.streamParser.buildRequestHeader(request.getHeaders());
		byte[] content = (byte[]) request.getBody();
		content = this.streamParser.joinRequestStream(this.session, new Stream(header, content));

		this.socket.getOutputStream().write(content);
		this.socket.getOutputStream().flush();

		System.out.println("send message:" + new String(content,request.getCharacterEncoding()));

	}
}
