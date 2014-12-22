package com.gatesocket.utils;

public interface Lifecycle {

	  void start() throws LifecycleException;

	  void stop() throws LifecycleException;

	  boolean isRunning();
}