package com.gatesocket.channel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gatesocket.Request;
import com.gatesocket.Response;
//import com.gatesocket.session.SessionContextListener;
//import com.gatesocket.session.SessionEventPublisher;
//import com.gatesocket.session.impl.DefaultSessionEventPublisher;

/**
 * 
 * @Creator FX
 * @Create-Date 2012-5-7
 */
public abstract class AbstractChannel implements Channel {


	private String name;


	protected String charset = "UTF-8";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}


	public Response process(Request request) throws ChannelException, IOException {
		Response response = new Response();
		response.setCharacterEncoding(this.charset);
		if (!request.getCharacterEncoding().equals(this.charset)) {
			System.out.println("Request Character Set is:" + request.getCharacterEncoding());
			System.out.println("Channel Character Set is:" + this.charset);
		}
		this.process(request, response);
		return response;
	}


}
