package com.gatesocket.channel.transport.socket.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.List;

import com.gatesocket.utils.BytesUtil;
import com.gatesocket.Request;
import com.gatesocket.Response;
import com.gatesocket.channel.ChannelException;
import com.gatesocket.channel.stream.RequestHeader;
import com.gatesocket.channel.stream.Stream;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-8
 */
public class SocketInteractionChannel extends SocketChannel {


	public void process(Request request, Response response) throws ChannelException, IOException {
		if (!this.isConnected()) {
			throw new ChannelException("this socket interaction channel has not connect remote server!!");
		}

		if (this.socket.isClosed()) {
			this.connect();
		}

		RequestHeader header = this.streamParser.buildRequestHeader(request.getHeaders());
		byte[] content = (byte[]) request.getBody();

		content = this.streamParser.joinRequestStream(this.session, new Stream(header, content));

		System.out.println("Send Message:[" + new String(content, request.getCharacterEncoding()) + "]");
		this.socket.getOutputStream().write(content);
		this.socket.getOutputStream().flush();
		
		
		
		
		
		

//		InputStream is = this.socket.getInputStream();

//		int length = 0;

//		ByteArrayOutputStream os = new ByteArrayOutputStream();

//		try {
//			byte t = (byte) is.read();
//			os.write(new byte[] { t });
//		} catch (InterruptedIOException e) {
//			System.out.println(e.getMessage());
//			response.setTimeout();
//			response.setErrorCode(Response.ERR_TIME_OUT);
//			try {
//				os.close();
//			} catch (Exception tr) {
//
//			}
//			return;
//		}

//		try {
//			while (true) {
//				byte[] buffer = new byte[1024];

//				if ((length = is.read(buffer, 0, 1024)) == -1) {
//					response.setErrorCode(Response.ERR_NO_RESP);
//				}
//				byte[] temp = BytesUtil.read(buffer, length);
//				os.write(temp);
//				System.out.println("Received Message:[" + new String(os.toByteArray(), response.getCharacterEncoding())
//								+ "]");
//				List<Stream> stream = this.streamParser.parseResponseStream(session, os.toByteArray());
//				os.reset();
//				if (stream.size() > 0) {
//					Stream retValue = stream.get(0);
//					System.out.println(
//							"Response Message:[" + new String(retValue.toBytes(), response.getCharacterEncoding())
//									+ "]");
//					response.setHeaders(retValue.getHeader().toMap());
//					response.setBody(retValue.getMessage());
//					return;
//				}
//			}
//		} catch (InterruptedIOException e) {
//			System.out.println(e.getMessage());
//			response.setTimeout();
//			response.setErrorCode(Response.ERR_TIME_OUT);
//			return;
//		} finally {
//			try {
//				os.close();
//			} catch (Throwable tr) {
//
//			}
//		}

	}

}
