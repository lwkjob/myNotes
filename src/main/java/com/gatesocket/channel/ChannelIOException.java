package com.gatesocket.channel;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-8
 */
public class ChannelIOException extends ChannelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1970550527055116590L;

	public ChannelIOException() {
		super();
	}

	public ChannelIOException(String msg, Throwable rootCause) {
		super(msg, rootCause);
	}

	public ChannelIOException(String msg) {
		super(msg);
	}

	public ChannelIOException(Throwable rootCause) {
		super(rootCause);
	}

}
