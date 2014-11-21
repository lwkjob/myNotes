package com.gatesocket.channel.stream;

import com.gatesocket.channel.stream.AbstractStreamParser;
import com.gatesocket.channel.stream.RequestHeader;
import com.gatesocket.channel.stream.ResponseHeader;

/**
 * support (message length header)
 * 
 * this simple header contain only one domain(MSG_LEN)
 * 
 * @Creator Fx
 * @Create-Date 2012-5-30
 */
public class SimpleStreamParser extends AbstractStreamParser {

	/**
	 * length of domain(MSG_LEN)
	 */
	protected int length = 8;

	@Override
	protected RequestHeader newRequestHeader() {
		return new SimpleHeader(this.length);
	}

	@Override
	protected ResponseHeader newResponseHeader() {
		return new SimpleHeader(this.length);
	}

	public void setLength(int length) {
		this.length = length;
	}

}
