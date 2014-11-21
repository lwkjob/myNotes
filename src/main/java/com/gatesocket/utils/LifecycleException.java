package com.gatesocket.utils;

public class LifecycleException extends RuntimeException
{

	private static final long serialVersionUID = 0x2f62384794539028L;

	public LifecycleException()
	{
	}

	public LifecycleException(String msg, Throwable rootCause)
	{
		super(msg, rootCause);
	}

	public LifecycleException(String msg)
	{
		super(msg);
	}

	public LifecycleException(Throwable rootCause)
	{
		super(rootCause);
	}
}