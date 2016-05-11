package com.lwk.socket.niodemo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.ByteBuffer;

import sun.nio.ch.DirectBuffer;

/**
 * 利用多态实现 释放直接内存引用
 * @author lwkjob
 *
 */
public class DirectByteBufferCleaner {

	public static void clean(final ByteBuffer byteBuffer) {
		if (byteBuffer.isDirect()) {
			((DirectBuffer) byteBuffer).cleaner().clean();
		}
	}

	public static void sleep(long i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			/* skip */
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("哈鲁"); 
		new Thread(new Runnable() {
			@Override
			public void run() {
					sleep(60*60 * 1000);
				}	
		}, "funck lwk").start();
		sleep(10000);
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 10);
		BufferedReader ss=new BufferedReader(new FileReader(new File("")));
		ss.read();
		System.out.println("start");
		sleep(60 * 10000);
		clean(buffer);
		System.out.println("end");
		sleep(10000);
		
	}
}