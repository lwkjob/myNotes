package com.lwk.socket.customer;
import java.net.Socket;
import java.util.Scanner;

/**
 * 输入流和输出流 分开任务
 * @author lwkjob
 *
 */
public class SocketClient {

	public static void main(String []args)  {
		Scanner scanner = new Scanner(System.in);
		SocketWrapper socketWrapper = null;
		try {
			socketWrapper = new SocketWrapper(new Socket("localhost" , 8888));
			System.out.println("已经连接上服务器端，现在可以输入数据开始通信了");
			String sendMsg = null;
			socketWrapper.writeLine(scanner.nextLine());//发送消息
			String recivedMsg = socketWrapper.readLine();
			while(!"close".equals(recivedMsg)) {
				System.out.println("===【服务器返回】===>" + recivedMsg);
				sendMsg = scanner.nextLine();
				socketWrapper.writeLine(sendMsg);//发送消息
				recivedMsg = socketWrapper.readLine();
			}
			//如果有一端已经关闭了 还继续写数据 会报错Software caused connection abort: socket write error
//			socketWrapper.writeLine("我再继续写1");
//			socketWrapper.writeLine("我再继续写222");
//			System.out.println("我是客户端，结束了2!");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(socketWrapper != null)
				socketWrapper.close();
		}
	}
}