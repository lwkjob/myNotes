package com.gatesocket.session;

import com.gatesocket.channel.ChannelException;

/**
 * 
 * @Creator Fx
 * @Create-Date 2012-5-14
 */
public class SessionContextException extends ChannelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2731517529392886323L;

	public SessionContextException() {
		super();
	}

	public SessionContextException(String msg, Throwable rootCause) {
		super(msg, rootCause);
	}

	public SessionContextException(String msg) {
		super(msg);
	}

	public SessionContextException(Throwable rootCause) {
		super(rootCause);
	}

}
