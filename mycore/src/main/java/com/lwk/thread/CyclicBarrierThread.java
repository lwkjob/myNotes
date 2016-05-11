package com.lwk.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可以循环的栏杆,类似于集合旗
 * @author lwkjob
 *
 */
public class CyclicBarrierThread {
	public static void main(String[] args) {
		ExecutorService exService=Executors.newCachedThreadPool();
		//创建一个栏杆，需要拦住3个线程
		final CyclicBarrier cb=new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable=new Runnable() {
				@Override
				public void run() {
					 try {
						 //随机休息时间
						Thread.sleep((long)(Math.random()*1000));
						System.out.println(Thread.currentThread().getName()+"到了1，现在1有"+ (cb.getNumberWaiting()+1));
						cb.await();
						//又随机休息时间
						Thread.sleep((long)(Math.random()*1000));
						System.out.println(Thread.currentThread().getName()+"到了2，现在2有"+ (cb.getNumberWaiting()+1));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			};
			exService.execute(runnable);
		}
		exService.shutdown();
		
	}
}
