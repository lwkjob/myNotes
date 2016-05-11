package com.lwk.socket.niodemo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;

/**
 * http://colobu.com/2014/11/13/java-aio-introduction/
 * 
 * 异步channel API提供了两种方式监控/控制异步操作(connect,accept, read，write等)。
 * 第一种方式是返回java.util.concurrent.Future对象， 
 * 检查Future的状态可以得到操作是否完成还是失败，还是进行中，
 *  future.get阻塞当前进程。 
 *  第二种方式为操作提供一个回调参数java.nio.channels.CompletionHandler，
 *  这个回调类包含completed,failed两个方法。
 *   channel的每个I/O操作都为这两种方式提供了相应的方法， 
 *   你可以根据自己的需要选择合适的方式编程。
 * @author lwkjob
 *
 */
public class Client {
	public static void main(String[] args) throws Exception {
		final AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
		
		Future<Void> future = client.connect(new InetSocketAddress("127.0.0.1", 8013));
		future.get();
		
		final ByteBuffer buffer = ByteBuffer.allocate(100);
		
		client.read(buffer, null, new CompletionHandler<Integer, Void>() {
		
			@Override
			public void completed(Integer result, Void attachment) {
				System.out.println("client received: " + new String(buffer.array()));
			}
			
			@Override
			public void failed(Throwable exc, Void attachment) {
				exc.printStackTrace();
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
								
			}
		});
		System.out.println("妈蛋");
		Thread.sleep(1000);
		System.out.println("妈蛋22");
	}
}