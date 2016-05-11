package com.lwk.socket.simple;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * 
 * 输入流和输出流 分开任务
 * @author lwkjob
 *
 */
public class SocketServer {

	public static void main(String []args) throws IOException {
		ServerSocket serSocket = new ServerSocket();
		serSocket.bind(new InetSocketAddress("10.28.3.13",8888));
		System.out.println("端口已经打开为8888，开始准备接受数据");
		SocketWrapper socketWrapper = null;
		try {
			socketWrapper = new SocketWrapper(serSocket.accept());
			String line = socketWrapper.readLine();
			while(!"bye".equals(line)) {
				System.out.println("客户端传来数据：" + line);
				socketWrapper.writeLine("我接收到你的数据：" + line);
				line = socketWrapper.readLine();
			}
			socketWrapper.writeLine("close");
		}finally {
			if(socketWrapper != null)
				socketWrapper.close();
		}
		
	}
}
