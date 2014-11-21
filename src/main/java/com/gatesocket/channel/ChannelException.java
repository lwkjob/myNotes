package com.gatesocket.channel;


/**
 * 
 * @Creator Fx
 * @Create-Date 2012-04-26
 */
public class ChannelException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4637794548391046L;

	public ChannelException() {
		super();
	}

	public ChannelException(String msg, Throwable rootCause) {
		super(msg, rootCause);
	}

	public ChannelException(String msg) {
		super(msg);
	}

	public ChannelException(Throwable rootCause) {
		super(rootCause);
	}
	
	

}
