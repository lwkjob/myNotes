package com.lwk.thread.jdk4Thread;

/**
 * @author lwk
 * 
 *         线程安全，线程同步，线程间通信
 * 
 */
class ProducerNew implements Runnable {
	QNew q;

	public ProducerNew(QNew q) {
		this.q = q;
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			if (i == 0) {
				q.put("zhangsan", "nan");
			} else {
				q.put("lisi", "nv");
			}
			i = (i + 1) % 2;
		}

	}
}

class ConsumerNew implements Runnable {
	QNew q;

	public ConsumerNew(QNew q) {
		this.q = q;
	}

	@Override
	public void run() {
		while (true) {
			q.get();
		}
	}
}

class QNew {
	private String name;
	private String sex;
	boolean full = false;

	public synchronized void get() {
		if (!full)
			waits();
		System.out.print(this.name);
		System.out.println(":" + this.sex);
		full = false;
		notify();
	}

	public synchronized void put(String name, String sex) {
		if (full)
			waits();

		this.name = name;
		sleep();
		this.sex = sex;
		full = true;
		notify();
	}

	// 睡觉
	private void sleep() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void waits() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ProducerOrConsumer2 {
	public static void main(String[] args) {
		QNew q = new QNew();
		ProducerNew p = new ProducerNew(q);
		ConsumerNew c = new ConsumerNew(q);
		new Thread(c).start();
		new Thread(p).start();
	}
}