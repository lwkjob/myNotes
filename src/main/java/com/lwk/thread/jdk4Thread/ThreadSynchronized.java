package com.lwk.thread.jdk4Thread;

public class ThreadSynchronized {
	public static void main(String[] args) throws InterruptedException {
		threadStart();
//		mainStart();
	}

	public static void threadStart() {
		TestThread ts = new TestThread();
		Thread th1=new Thread(ts);
		Thread th2=new Thread(ts);
		Thread th3=new Thread(ts);
		Thread th4=new Thread(ts);
		// ts.setDaemon(true);
		// ts.start();
		// ts.join(2 * 1000);
//		th1.setDaemon(true);//后台线程
		th1.start();
		th2.start();
		th3.start();
		th4.start();
	}

	public static void mainStart() {
		while (true) {
			System.out.println("mian 我的哥哥");
		}
	}
}

class TestThread implements Runnable { // extends Thread
	int num = 100;
	public void run() {
		while (true) {
			synchronizedArea();// 同步代码块
//			synchronizedMethod();// 同步方法
		}
	}
	
	// 方法同步
	private synchronized void synchronizedMethod(){
		if (num > 0) {
			sleep();
			print();
		} else {
			return;
		}
		num--;
	}
	// 代码块同步
	private void synchronizedArea(){
		synchronized (this) {
			if (num > 0) {
				sleep();
				print();
			} else {
				return;
			}
			num--;
		}
	}
	//睡觉
	private void sleep(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//打印
	private void print(){
		System.out.println("run testhTread  "
				+ Thread.currentThread().getName() + ":"
				+ Thread.currentThread().getId()+"号码"+num);
	}
}