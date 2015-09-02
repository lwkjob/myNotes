package com.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	private Socket socket;
	private int port = 5055;
 
	private void start() throws Exception {
		serverSocket = new ServerSocket(port);
		while (true) {
			socket = serverSocket.accept();
			new Thread(new SocketThread(socket)).start();
		}
	}
 
	/**
	 * 处理socket连接s
	 */
	class SocketThread implements Runnable {
		private Socket socket;
 
		public SocketThread(Socket socket) {
			this.socket = socket;
		}
 
		public void run() {
			try {
				DataInputStream reader =  new DataInputStream(socket.getInputStream());
				DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
			    System.out.println("服务端收到:" + reader.readUTF()+Thread.currentThread().getName());
				writer.writeUTF("this is server ");
				writer.flush();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					if (!socket.isClosed()) {
						try {
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
 
			}
		}
 
	}
 
	public static void main(String[] args) {
		Server s = new Server();
		try {
			s.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}