package com.lwk.thread.pool.communicationpool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * 该类为多线程类，用于服务端
 */
public class ServerThread implements Runnable {

	private Socket client = null;

	public ServerThread(Socket client) {
		this.client = client;
	}


	@Override
	public void run() {
		try {
			Thread.sleep(100);
			// 获取Socket的输出流，用来向客户端发送数据
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			// 获取Socket的输入流，用来接收从客户端发送过来的数据
			DataInputStream in = new DataInputStream(client.getInputStream());
					
			System.out.println(in.readUTF()+ Thread.currentThread().getName());
			
			// 将接收到的字符串前面加上echo，发送到对应的客户端
			out.writeUTF("bye");
			out.flush();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
