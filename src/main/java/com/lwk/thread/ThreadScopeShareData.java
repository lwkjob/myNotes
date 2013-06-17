package com.lwk.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lwk
 * 用HashMap自己模拟，线程范围内的数据共享
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
