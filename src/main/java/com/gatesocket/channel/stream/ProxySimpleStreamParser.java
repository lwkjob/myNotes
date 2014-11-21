package com.gatesocket.channel.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gatesocket.channel.stream.RequestHeader;
import com.gatesocket.channel.stream.ResponseHeader;
import com.gatesocket.channel.stream.Stream;
import com.gatesocket.channel.stream.StreamParser;
import com.gatesocket.session.Session;

/**
 * 
 * @Creator Fx
 * @Create-Date 2011-5-25
 */
public class ProxySimpleStreamParser extends SimpleStreamParser {

	private StreamParser streamParser;

	public ProxySimpleStreamParser(StreamParser streamParser) {
		this.streamParser = streamParser;
	}

	@Override
	public RequestHeader buildRequestHeader(Map<String, String> map) {
		return this.streamParser.buildRequestHeader(map);
	}

	@Override
	public ResponseHeader buildResponseHeader(Map<String, String> map) {
		return this.streamParser.buildResponseHeader(map);
	}

	@Override
	public byte[] joinRequestStream(Session session, Stream stream) {
		byte[] bs = this.streamParser.joinRequestStream(session, stream);
		Stream ss = new Stream(this.newRequestHeader(), bs);
		byte[] bytes = super.joinRequestStream(session, ss);
		return bytes;
	}

	@Override
	public byte[] joinResponseStream(Session session, Stream stream) {
		byte[] bs = this.streamParser.joinResponseStream(session, stream);
		Stream ss = new Stream(this.newResponseHeader(), bs);
		return super.joinResponseStream(session, ss);
	}

	@Override
	public List<Stream> parseRequestStream(Session session, byte[] msg) {
		List<Stream> ls = super.parseRequestStream(session, msg);
		List<Stream> retValue = new ArrayList<Stream>();
		for (Iterator<Stream> itr = ls.iterator(); itr.hasNext();) {
			Stream ss = itr.next();
			List<Stream> temp = this.streamParser.parseRequestStream(session, ss.getMessage());
			retValue.addAll(temp);
		}
		return retValue;

	}

	@Override
	public List<Stream> parseResponseStream(Session session, byte[] msg) {
		List<Stream> ls = super.parseResponseStream(session, msg);
		List<Stream> retValue = new ArrayList<Stream>();
		for (Iterator<Stream> itr = ls.iterator(); itr.hasNext();) {
			Stream ss = itr.next();
			List<Stream> temp = this.streamParser.parseResponseStream(session, ss.getMessage());
			retValue.addAll(temp);
		}
		return retValue;
	}

}
