package com.lwk.thread.jdk4Thread;

class Producer implements Runnable {
	Q q;

	public Producer(Q q) {
		this.q = q;
	}
 
	@Override
	public void run() {
		int i = 0;

		while (true) {
			synchronized (q) {
				if (!q.full) {
					if (i == 0) {
						q.name = "zhangsan";
						sleep();
						q.sex = "nan";
					} else {
						q.name = "lisi";
						q.sex = "nv";
					}
					q.full = true;
					q.notify();// 通知消费者线程
					i = (i + 1) % 2;
				} else {
					waits(q);
				}
			}
		}

	}

	// 睡觉
	private void sleep() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void waits(Q q) {
		try {
			q.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer implements Runnable {
	Q q;

	public Consumer(Q q) {
		this.q = q;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (q) {
				if (q.full) {
					System.out.print(q.name);
					System.out.println(":" + q.sex);
					q.full = false;
					q.notify();// 通知等待的生产者线程
				} else {
					waits(q);
				}
				
			}
		}
	}

	private void waits(Q q) {
		try {
			q.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Q {
	String name = "null";
	String sex = "null";
	boolean full = false;
}

public class ProducerOrConsumer {
	public static void main(String[] args) {
		Q q = new Q();
		Producer p = new Producer(q);
		Consumer c = new Consumer(q);
		new Thread(c).start();
		new Thread(p).start();
	}
}