package com.lwk.socket.netty.threadlocal;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * https://my.oschina.net/andylucc/blog/614359
 * 同一个线程 操作多个threadlocal会有性能问题
 * Created by lwk on 2016/11/3.
 */
public class FsatTheadLocalTest {


    public void localTest() {

        Thread mainThread = Thread.currentThread();
        final int threadCount = 1000;
        ThreadLocal<Integer>[] threadLocals = new ThreadLocal[threadCount];
        for (int i = 0; i < threadCount; i++) {
            //初始化1000个threadlocal
            threadLocals[i] = new ThreadLocal<>();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long m=0;
//                long start = System.nanoTime();
                //一个线程启用1000个threadlocal
                for (int i = 0; i < threadCount; i++) {
                    threadLocals[i].set(1);
                }
                long start = System.nanoTime();
                for (int i = 0; i < threadCount; i++) {
                    //每个threadlocal读100w次
                    for (int j = 0, count = 10000 * 100; j < count; j++) {
                          m=m+  threadLocals[i].get();

                    }
                }
                long end = System.nanoTime();
                System.out.println("take[" + TimeUnit.NANOSECONDS.toMillis(end - start) + "]ms "+m);
                LockSupport.unpark(mainThread);
            }
        });
        thread.start();
        LockSupport.park(mainThread);

    }

    public void fastLocalTest() {

        Thread mainThread = Thread.currentThread();
        final int threadCount = 1000;
        FastThreadLocal<Integer>[] threadLocals = new FastThreadLocal[threadCount];
        for (int i = 0; i < threadCount; i++) {
            //初始化1000个FastThreadLocal
            threadLocals[i] = new FastThreadLocal<>();
        }

        Thread thread = new FastThreadLocalThread(new Runnable() {
            @Override
            public void run() {
                long m=0;
//                long start = System.nanoTime();
                //一个线程启用1000个threadlocal
                for (int i = 0; i < threadCount; i++) {
                    threadLocals[i].set(1);
                }
                long start = System.nanoTime();
                for (int i = 0; i < threadCount; i++) {
                    //每个threadlocal读100w次
                    for (int j = 0, count = 10000 * 100; j < count; j++) {
                         m= m+ threadLocals[i].get();
                    }
                }
                long end = System.nanoTime();
                System.out.println("fast take[" + TimeUnit.NANOSECONDS.toMillis(end - start) + "]ms "+m);
                LockSupport.unpark(mainThread);
            }
        });
        thread.start();
        LockSupport.park(mainThread);

    }



    public static void main(String[] args) {
        new FsatTheadLocalTest().localTest();
        new FsatTheadLocalTest().fastLocalTest();
    }
}
