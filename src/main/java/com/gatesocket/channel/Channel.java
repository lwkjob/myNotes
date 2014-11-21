package com.gatesocket.channel;

import java.io.IOException;

import com.gatesocket.Request;
import com.gatesocket.Response;
import com.gatesocket.session.SessionContextListener;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-7
 */
public interface Channel {

	void setName(String name);

	String getName();
	
	String getCharset();
	
	void setCharset(String charset);

//	void addSessionContextListener(SessionContextListener listener);

	void connect() throws ChannelException, IOException;

	void close() throws ChannelException, IOException;

	boolean isConnected();

	Response process(Request request) throws ChannelException, IOException;
	
	void process(Request request,Response response)throws ChannelException,IOException;
	
	
}
