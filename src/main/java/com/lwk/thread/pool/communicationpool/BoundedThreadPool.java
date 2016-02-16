package com.lwk.thread.pool.communicationpool;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.*;

/**
 *
 * 有等待队列边界的线程池
 * 相对于{@link ServerExecutor}使用{@link BoundedThreadPool#newBoundedFixedThreadPool}方法创建线程池
 *
 * Created by dell-pc on 2016/2/16.
 */
public class BoundedThreadPool {


    /**
     * 自定义创建一个有边界的线程池
     * @param nThreads 线程池大小
     * @param capacity 等待队列的大小
     * @return
     */
    public static ExecutorService newBoundedFixedThreadPool(int nThreads,int capacity){
                 return  new ThreadPoolExecutor(
                         nThreads,
                         nThreads,
                         0L,
                         TimeUnit.MILLISECONDS,
                         new LinkedBlockingQueue<Runnable>(capacity),
                         new ThreadPoolExecutor.DiscardPolicy() //丢弃超限的请求

                 ) ;
    }

    public static void main(String[] args) throws IOException {

        new Date("1212");

        //服务端在20006端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(20006);
        Socket client = null;
         //********5个核心线程，最大线程数为5，最大等待队列为16
        Executor service =newBoundedFixedThreadPool(5,16);
        int i=0;
        while(true){
            //等待客户端的连接
            client = server.accept();
            //调用execute()方法时，如果必要，会创建一个新的线程来处理任务，但它首先会尝试使用已有的线程，
            //如果一个线程空闲60秒以上，则将其移除线程池；
            //另外，任务是在Executor的内部排队，而不是在网络中排队
            service.execute(new ServerThread(client));
        }
    }
}
