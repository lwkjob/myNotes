package com.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client { 
	private String host = "127.0.0.1";
	private int port = 5055;
 
 
	/**
	 * 数据接收线程
	 */
 
	private void start() {

		new Thread(new Runnable() {
					@Override
					public void run() {
						Socket socket=null;
						try {
							socket = new Socket(host, port);
							DataInputStream in=new DataInputStream(socket.getInputStream());
							DataOutputStream out=new DataOutputStream(socket.getOutputStream());
//							byte[] inData=new byte[1024];
//							while(in.read(inData)!=-1){
//								
//							}
							try {
								Thread.sleep(100000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							out.writeUTF("哈喽baby");
							out.flush();
							System.out.println(in.readUTF()+"lwk我收到的");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
		}).start();
					
	}
 
	public static void main(String[] args) {
		new Client().start();
	}
}