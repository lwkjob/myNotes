package com.lwk.socket.netty.threadlocal;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by lwk on 2016/11/3.
 */
public class FsatTheadLocalTest {



    public void localTest(){

        Thread mainThread=Thread.currentThread();
        final int threadCount=1000;
        ThreadLocal<String>[] threadLocals=new ThreadLocal[threadCount];
        for (int i = 0; i <threadCount ; i++) {
                    threadLocals[i]=new ThreadLocal<>();
        }

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();
        LockSupport.park(mainThread);

    }
}
