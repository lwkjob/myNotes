package com.lwk.socket.keepalive;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端每接收一个请求开一个线程
  */
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
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				Writer writer = new OutputStreamWriter(socket.getOutputStream());
				String content;
				while ((content = reader.readLine()) != null) {
					System.out.println("receive:" + content);
					writer.write("this is server \r\n");
					writer.flush();
				}
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