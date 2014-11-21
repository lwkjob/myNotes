package com.gatesocket.utils;

public interface Lifecycle
{

	public abstract void start()
		throws LifecycleException;

	public abstract void stop()
		throws LifecycleException;

	public abstract boolean isRunning();
}