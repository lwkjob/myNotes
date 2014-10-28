package com.lwk.thread.pool.communicationpool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;

public class Client1 {
	public static void main(String[] args) throws IOException {
		//
		for (int i = 0; i < 100; i++) {
			final int n = i;
			Executors.newFixedThreadPool(100).execute(new Runnable() {

				@Override
				public void run() {

					Socket client = null;
					DataOutputStream out = null;
					try {
						// 客户端请求与本机在20006端口建立TCP连接
						client = new Socket("localhost", 20006);
						client.setSoTimeout(1000 * 10);
						// 获取键盘输入
						// 获取Socket的输出流，用来发送数据到服务端
						out = new DataOutputStream(client.getOutputStream());
						// 获取Socket的输入流，用来接收从服务端发送过来的数据
						DataInputStream in = new DataInputStream(client.getInputStream());
						out.writeUTF("你妹我是" + n);
						System.out.println("收到信息"+in.readUTF());
						out.flush();

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							out.close();
							client.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			});
		}

		//
	}
}