package com.lwk.thread.jdk4Thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lwk
 * 用HashMap自己模拟，线程范围内的数据共享
 * 每个线程被创建时候就给这个创建一份数据装进map里面，key就是这个线程的名称，
 * 这个在外部取的时候用这个线程名称取出来，这样就能实现线程本地变量
 */
public class ThreadScopeShareData {
	private static long data = 0;
	private static Map dataMap = new HashMap();
	
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (ThreadScopeShareData.class) {
//						data = new Random().nextInt();
						data = new Date().getTime();
						System.out.println(Thread.currentThread().getName()
								+ " 产生" + data);
						dataMap.put(Thread.currentThread().getName(), data);
						new A().get();
						new B().get();
					}
				}
			}).start();
		}
	}

	static class A {
		public void get() {
			System.out.println(Thread.currentThread().getName() + " A "
					+ dataMap.get(Thread.currentThread().getName()));
		}
	}

	static class B {
		public void get() {
			System.out.println(Thread.currentThread().getName() + " B "
					+ dataMap.get(Thread.currentThread().getName()));
		}
	}
}
