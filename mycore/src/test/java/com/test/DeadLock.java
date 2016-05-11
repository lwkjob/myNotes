package com.test;


/**
 * 死锁
 * @author lwkjob
 *
 */
public class DeadLock {
	public static void main(String[] args) {
		final Object obj1=new Object();
		final Object obj2=new Object();
		Thread lwkth1=new Thread(new Runnable() {
			@Override
			public void run() {
					synchronized (obj1) {
						System.out.println("1obj1");
						synchronized (obj2) {
							System.out.println("1obj2");
						}
						System.out.println("你死锁走不到这里来1");
				}
				
			}
		},"lwk1");
		
		Thread lwkth2=new Thread(new Runnable() {
			@Override
			public void run() {
					synchronized (obj2) {
						System.out.println("2obj2");
						synchronized (obj1) {
							System.out.println("2obj1");
						}
						System.out.println("你死锁走不到这里来2");
					}
			}
		},"lwk2");
		
		lwkth1.start();
		lwkth2.start();
	}
}
