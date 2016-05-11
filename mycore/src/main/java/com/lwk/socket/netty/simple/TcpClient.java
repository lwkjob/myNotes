package com.lwk.socket.netty.simple;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TcpClient {

	public static void main(String[] args) throws IOException {
		final 	Socket socket =  new Socket("localhost", 8080);;
		DataOutputStream out = null;
		DataInputStream in=null;
		BufferedReader bufferedReader=null;
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		try {
			socket.setTcpNoDelay(true);
			out = new DataOutputStream(socket.getOutputStream());
			//用一个单独的线程专门接受服务器来的回应,避免阻塞
			readFromServerOnNewThread(socket);
			// 请求服务器
			for (int i =0 ; i <10 ; i++) {
				String data="哈喽"+i;
				writeData(out, data);
			}
			writeData(out, "bye");
		  //in = getMsg2(socket, byteArrayOutputStream);
		} finally {
			// 关闭连接
			out.close();
			// in.close();
			socket.close();
		}
	}

	private static void readFromServerOnNewThread(final Socket socket) {
		new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getMsg(socket);
                        }catch (Exception e){

                        }
                    }
                }
        ).start();
	}

	private static void writeData(DataOutputStream out, String data1) throws IOException {

		byte[] outputBytes1 = data1.getBytes("UTF-8");
		out.writeInt(outputBytes1.length); // write header
		out.write(outputBytes1); // write body
		out.flush();
		try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){

        }
	}

	private static void getMsg(Socket socket ) throws IOException {
		BufferedReader bufferedReader;
		bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String serverMsg=null;
		while ((serverMsg=bufferedReader.readLine())!=null){
            System.out.println(serverMsg);
        }
		bufferedReader.close();
	}

	private static DataInputStream getMsg2(Socket socket, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
		DataInputStream in;
		in=new DataInputStream(socket.getInputStream());
		int len=0;
		byte[] serverMsgBuffer=new byte[3];
		while ((len=in.read(serverMsgBuffer))!=-1){
            byteArrayOutputStream.write(serverMsgBuffer,0,len);
        }
		System.out.println(new String(byteArrayOutputStream.toByteArray()));
		return in;
	}

}