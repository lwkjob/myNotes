package com.lwk.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时计数器线程
 * 
 * @author lwkjob
 * 
 */
public class CountDownLatchThread {
	public static void main(String[] args) {
		ExecutorService exService=Executors.newCachedThreadPool();
		
		//倒数一声 一个人通知多个人
		final CountDownLatch cdl=new CountDownLatch(1);
		
		
		final CountDownLatch cdl2=new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable=new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long)(Math.random()*1000));	
						System.out.println(Thread.currentThread().getName()+"正在等待结果");
						cdl.await();//起跑线
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println(Thread.currentThread().getName()+"开始走");
						cdl2.countDown();//通知记分员我跑完了
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			};
			exService.execute(runnable);
		}
		exService.shutdown();
		try {
			Thread.sleep((long)(Math.random()*10000));
			System.out.println("准备发令");
			cdl.countDown();
			cdl2.await();
			System.out.println("大家都跑完");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
		
		
		
		
	}	
}
