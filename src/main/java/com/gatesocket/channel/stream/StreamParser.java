package com.gatesocket.channel.stream;

import java.util.List;
import java.util.Map;

import com.gatesocket.session.Session;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-8
 */
public interface StreamParser {

	public static final String STREAM_ATTACHMENT_REQUST = "stream_attachment_request";

	public static final String STREAM_ATTACHMENT_RESPONSE = "stream_attachment_response";
	
	String getCharset();
	
	void setCharset(String charset);

	List<Stream> parseRequestStream(Session session, byte[] msg);

	List<Stream> parseResponseStream(Session session, byte[] msg);

	byte[] joinResponseStream(Session session, Stream stream);

	byte[] joinRequestStream(Session session, Stream stream);

	ResponseHeader buildResponseHeader(Map<String, String> map);

	RequestHeader buildRequestHeader(Map<String, String> map);
}
