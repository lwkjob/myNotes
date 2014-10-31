package com.lwk.socket.multiType.server;
import static com.lwk.socket.multiType.Commons.SERVER_SAVE_BASE_PATH;
import static com.lwk.socket.multiType.Commons.logInfo;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lwk.socket.multiType.SocketWrapper;

public class SocketServerMain {
	
//	private final static List<Worker> workers = new ArrayList<Worker>();
	static ExecutorService executors=Executors.newFixedThreadPool(5);

	public static void main(String []args) throws IOException {
		initPath();
		ServerSocket serverSocket = new ServerSocket(8888);
		logInfo("端口已经打开为8888，开始准备接受数据.....");
		try {
			while(true) {
				SocketWrapper socketWrapper = new SocketWrapper(serverSocket.accept());
//				workers.add(new Worker(socketWrapper , "socket_thread_" + index++));
				executors.execute(new Worker(socketWrapper , Thread.currentThread().getName()));
			}
		}finally {
			serverSocket.close();
			executors.shutdown();
		}
	}
	
	private static void initPath() {
		File file = new File(SERVER_SAVE_BASE_PATH);
		if(!file.exists()) {
			boolean success = file.mkdirs();
			if(!success) 
				throw new RuntimeException("无法创建目录：" + SERVER_SAVE_BASE_PATH);
		}
	}
}
