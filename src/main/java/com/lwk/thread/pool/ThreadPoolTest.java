package com.lwk.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lwk
 * 线程池
 */
public class ThreadPoolTest {
	public static void main(String[] args) {
		//		固定大小线程池
		ExecutorService threadPool=Executors.newFixedThreadPool(10);
		
//		ExecutorService cachedThreadpool=Executors.newCachedThreadPool();
//		//
//		ExecutorService threadpoolOne=Executors.newSingleThreadExecutor();
		
		for (int i = 1; i < 8; i++) {
			final int now=i;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 8; j++) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+":"+now);
					}
				}
			});
		}
		//定时任务，固定频率
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("sss");
			}
		}, 2, 3, TimeUnit.SECONDS);
		
		//定时任务
		Executors.newScheduledThreadPool(3).schedule(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("sss");
			}
		}, 2, TimeUnit.SECONDS );
	}
}
