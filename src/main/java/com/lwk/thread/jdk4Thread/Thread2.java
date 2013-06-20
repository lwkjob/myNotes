package com.lwk.thread.jdk4Thread;

/**
 * @author lwk
 * 如果子类重写了Thread的run方法，则Runnable里面的run方法将不会被执行
 */
public class Thread2 {

	public static void main(String[] args) {
		new Thread() {
			public void run() {
				boolean stop = false;
				int i = 0;
				while (!stop) {
					if (i == 9)
						stop = true;
					System.out.println(i + "你好啊！"
							+ Thread.currentThread().getName());
					i++;
				}
			};
		}.start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("你好啊！" + Thread.currentThread().getName());
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("弟弟");
			}
		}) {
			public void run() {
				System.out.println("哥哥");
			};
		}.start();
	}

	// 睡觉方法
	private static void sleepOne() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
