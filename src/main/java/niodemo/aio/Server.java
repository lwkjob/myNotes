package niodemo.aio;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/**
 * 
 * 
 * BIO，同步阻塞式IO，简单理解：一个连接一个线程
 * NIO，同步非阻塞IO，简单理解：一个请求一个线程(实际阻塞在系统底层,多路复用epoll)
 * AIO，异步非阻塞IO，简单理解：全交给系统线程
 * 
 * @author lwkjob
 */
public class Server {
	//mac os ides test
	private static Charset charset = Charset.forName("US-ASCII");
    private static CharsetEncoder encoder = charset.newEncoder();
	
	public static void main(String[] args) throws Exception {
		
		AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
		
		final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress("0.0.0.0", 8013));
		
		//异步非阻塞
		server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
		
			@Override    //异步回调读
			public void completed(AsynchronousSocketChannel result, Void attachment) {
				
				try {
					 String now = new Date().toString();
					 ByteBuffer buffer = encoder.encode(CharBuffer.wrap(now + "\r\n"));
					//result.write(buffer, null, new CompletionHandler<Integer,Void>(){...}); //callback or
					 
					 //异步阻塞写
					Future<Integer> f = result.write(buffer);
					f.get();
					System.out.println("sent to client: " + now);
					result.close();
				} catch (IOException | InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}finally{
					server.accept(null, this); // 接受下一个连接
				}
			}
			
			@Override
			public void failed(Throwable exc, Void attachment) {
				exc.printStackTrace();
			}
			
		});
		group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
	}
}