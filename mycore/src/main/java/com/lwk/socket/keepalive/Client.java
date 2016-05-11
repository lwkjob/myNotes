package com.lwk.socket.keepalive;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
 
public class Client { 
	private String host = "10.28.3.13";
	private int port = 5055;
 
	/**
	 * 数据发送线程
	 */
	class SendThread implements Runnable {
		private Socket socket;
 
		public SendThread(Socket socket) {
			this.socket = socket;
		}
 
		public void run() {
			while (true) {
				try {
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					pw.write("this is client \r\n");
					pw.flush();
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
 
	/**
	 * 数据接收线程
	 */
	class ReceiveThread implements Runnable {
		private Socket socket;
 
		public ReceiveThread(Socket socket) {
			this.socket = socket;
		}
 
		public void run() {
			while (true) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String content;
					while ((content = reader.readLine()) != null) {
						System.out.println("receive:" + content);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
 
	private void start() {
		try {
			Socket socket = new Socket(host, port);// 创建Socket
			new Thread(new SendThread(socket)).start();// 启动读线程
			new Thread(new ReceiveThread(socket)).start();// 启动收线程
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public static void main(String[] args) {
		new Client().start();
	}
}