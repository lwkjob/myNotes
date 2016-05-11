package com.lwk.socket.netty.customer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TcpClientCustomer {

	public static void main(String[] args) throws IOException {

		Socket socket = null;
		DataOutputStream out = null;

		try {

			socket = new Socket("localhost", 8080);
			out = new DataOutputStream(socket.getOutputStream());

			// 请求服务器
			String data1 = "牛顿";
			byte[] outputBytes1 = data1.getBytes("UTF-8");
			int byteLength=outputBytes1.length;
			out.write(LittleEndian.toLittleEndian(byteLength)); // write header
			out.write(outputBytes1); // write body

			out.flush();

			TimeUnit.SECONDS.sleep(10);
		}catch (Exception e){
			System.out.println(e);
		}finally {
			// 关闭连接

			out.close();
			socket.close();
		}

	}

}