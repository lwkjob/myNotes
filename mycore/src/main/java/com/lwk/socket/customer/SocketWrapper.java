package com.lwk.socket.customer;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;
import java.net.Socket;

/**
 * 协议报文格式为: 1个字节的类型标记 4个字节的报文长度 最后是整个报文
 * 输入流和输出流 分开任务
 * @author lwkjob
 *
 */
public class SocketWrapper {

	private Socket socket;
	
	private DataInputStream dataInputStream;

	private DataOutputStream dataOutputStream;

	
	public SocketWrapper(Socket socket) throws IOException {
		this.socket = socket;
		this.dataInputStream= new DataInputStream(socket.getInputStream());
		this.dataOutputStream = new DataOutputStream(socket.getOutputStream())  ;
	}
	
	public String readLine() throws IOException {

		byte flag = dataInputStream.readByte();
		System.out.println("收到类型标记 flag="+flag);

		int len=dataInputStream.readInt();
		System.out.println("收到总数据长度 len="+len);

		System.out.println("消息数据 datalen="+(len-4-1));
		byte[] data=new byte[len-4-1];
		dataInputStream.readFully(data);
		String dataStr=new String(data);

		System.out.println("收到数据 data="+dataStr);
		return dataStr;
	}
	
	public void writeLine(String line) throws IOException {
		byte[] messageByte=(line).getBytes();
		 dataOutputStream.writeByte(2);
		dataOutputStream.writeInt(1+4+messageByte.length);
		dataOutputStream.write(messageByte);
		dataOutputStream.flush();
	}
	
	public void close() {
		try {
			this.socket.close();
		}catch(Exception e) {
			System.out.println("关闭连接报错");
			e.printStackTrace();
		}
	}
}
