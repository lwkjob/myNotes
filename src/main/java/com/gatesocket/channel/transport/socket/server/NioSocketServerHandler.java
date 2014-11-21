package com.gatesocket.channel.transport.socket.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.gatesocket.Request;
import com.gatesocket.Response;
import com.gatesocket.Transaction;
import com.gatesocket.channel.filter.DefaultFilterChain;
import com.gatesocket.channel.filter.Filter;
import com.gatesocket.channel.ChannelProcessor;
import com.gatesocket.channel.stream.Stream;
import com.gatesocket.channel.stream.StreamParser;
import com.gatesocket.session.NioSocketSession;
import com.gatesocket.session.SessionEventPublisher;
import com.gatesocket.session.SessionClosedEvent;
import com.gatesocket.session.SessionCreatedEvent;
import com.gatesocket.utils.testsc;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-14
 */
class NioSocketServerHandler extends IoHandlerAdapter {

//	protected static Log logger = LogFactory.getLog(NioSocketServerHandler.class);

	protected StreamParser streamParser;

	protected List<Filter> filters;

	protected ChannelProcessor channelProcessor;

	protected SessionEventPublisher sessionEventPublisher;

	protected String charset = "UTF-8";
	
	protected testsc testtt = new testsc();
	
	public void setCharset(String charset) {
		this.charset = charset;
	}

	public void setSessionEventPublisher(SessionEventPublisher sessionEventPublisher) {
		this.sessionEventPublisher = sessionEventPublisher;
	}
	
	

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("session["+session+"] has idle!!!");
		System.out.println("session will close");
		session.close(true);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println("remote connect session error occured[" + session.toString() + "]");
		System.out.println(cause.getMessage());
		System.out.println("current session will close now");
		session.close(true);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		sessionEventPublisher.publishSessionEvent(new SessionClosedEvent(new NioSocketSession(session)));
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		sessionEventPublisher.publishSessionEvent(new SessionCreatedEvent(new NioSocketSession(session)));
		super.sessionCreated(session);
	}

	@Override
	public void messageReceived(IoSession session, Object obj) throws Exception {
		byte[] buffer = new byte[] {};

		if (obj instanceof IoBuffer) {
			IoBuffer io = (IoBuffer) obj;
			int leng = io.limit();
//			leng = leng -2 ;
			System.out.println("buffer size:" + leng);
			byte[] b = new byte[leng];
			io.get(b, 0, b.length);
			buffer = b;
			StringBuffer stringBuffer = new StringBuffer();  
		    for (int i = 0; i < b.length; i++)  
		     {  
		  
		      stringBuffer.append((char) b [i]);  
		      }  
			System.out.println(buffer.length+stringBuffer.toString());
		} else if (obj instanceof byte[]) {
			buffer = (byte[]) obj;
		} else {
			throw new RuntimeException("not support session obj type[" + obj.getClass() + "]");
		}

		NioSocketSession ss = new NioSocketSession(session);
		List<Stream> stream = new ArrayList<Stream>();
		try {
			stream = this.streamParser.parseRequestStream(ss, buffer);
		} catch (Exception e) {
			System.out.println("parse stream error occured!!");
			System.out.println("message content:" + new String(buffer));
			throw e;
		}

		for (Iterator<Stream> itr = stream.iterator(); itr.hasNext();) {
			Stream sm = itr.next();
			System.out.println("Received Message:" + sm.toString());
			Request request = new Request(sm.getMessage(), sm.getHeader().toMap());
			Request msgrequest = new Request(sm.toString());
			request.setCharacterEncoding(this.charset);
			Response response = new Response();
			response.setCharacterEncoding(charset);
			
			
			
			DefaultFilterChain filterChain = new DefaultFilterChain(filters, this.channelProcessor);

			filterChain.doFilter(new Transaction(request, response, ss));

			byte[] respBody = (byte[]) response.getBody();

			if ((respBody != null && respBody.length != 0) || !response.getHeaders().isEmpty()) {
				Stream rsm = new Stream(this.streamParser.buildResponseHeader(response.getHeaders()), (byte[]) response
						.getBody());
				session.write(IoBuffer.wrap(this.streamParser.joinResponseStream(ss, rsm)));
//				// ss.setAttribute(StreamParser.STREAM_ATTACHMENT_REQUST, new
//				// byte[] {});
//				// ss.setAttribute(StreamParser.STREAM_ATTACHMENT_RESPONSE, new
//				// byte[] {});
			}
			if (ss.isFlushdedClose()) {
				ss.close();
			}
		}
	}

	public void setChannelProcessor(ChannelProcessor channelProcessor) {
		this.channelProcessor = channelProcessor;
	}

	public void setStreamParser(StreamParser streamParser) {
		this.streamParser = streamParser;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

}
