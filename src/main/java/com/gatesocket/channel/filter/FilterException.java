package com.gatesocket.channel.filter;

import com.gatesocket.channel.ChannelException;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-12
 */
public class FilterException extends ChannelException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilterException() {
		super();
	}

	public FilterException(String msg, Throwable rootCause) {
		super(msg, rootCause);
	}

	public FilterException(String msg) {
		super(msg);
	}

	public FilterException(Throwable rootCause) {
		super(rootCause);
	}

	
}
