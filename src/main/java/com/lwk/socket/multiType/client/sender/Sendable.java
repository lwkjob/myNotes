package com.lwk.socket.multiType.client.sender;

import java.io.IOException;

import com.lwk.socket.multiType.SocketWrapper;


public interface Sendable {
	
	public byte getSendType();

	public void sendContent(SocketWrapper socketWrapper) throws IOException;
}
