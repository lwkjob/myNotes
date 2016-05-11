package com.lwk.socket.mina.keepAlive;


import com.lwk.socket.mina.keepAlive.server.Initialization;
import com.lwk.socket.mina.keepAlive.server.MinaLongConnServer;
import com.lwk.socket.mina.keepAlive.server.MinaShortConnServer;

/**
 * @author lwkjob
 *
1）       启动服务端程序，监听8001和8002端口。
2）       长连接客户端向服务端8002端口建立连接，服务端将连接对象保存到共享内存中。由于采用长连接方式，连接对象是唯一的。
3）       短连接客户端向服务端8001端口建立连接。建立连接后创建一个连接对象。
4）       短连接客户端连接成功后发送数据。服务端接收到数据后从共享内存中得到长连接方式的连接对象，使用此对象向长连接客户端发送数据。发送前将短连接对象设为长连接对象的属性值。
5）       长连接客户端接收到数据后返回响应数据。服务端从长连接对象的属性中取得短连接对象，通过此对象将响应数据发送给短连接客户端。
6）       短连接客户端收到响应数据后，关闭连接。
 */
public class Main {
	public static void main(String[] args) throws Exception {
		Initialization.getInstance().init();
		MinaShortConnServer server1 = new MinaShortConnServer();
		server1.start();
		MinaLongConnServer server2 = new MinaLongConnServer();
		server2.start();
	}
}
