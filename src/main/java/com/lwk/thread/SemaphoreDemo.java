package com.lwk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * 用信号灯semaphore控制互斥，只有拿到灯的线程才可以执行
 * @author lwkjob
 *
 */
public class SemaphoreDemo {
		public static void main(String[] args) {
			ExecutorService executorService=Executors.newCachedThreadPool();
			//初始化3个信号灯，也就是最多只有3个线程同时执行
			final Semaphore semaphore=new Semaphore(3);
			for (int i = 0; i < 10; i++) {
				Runnable runnable=new Runnable() {
					@Override
					public void run() {
						try {
							semaphore.acquire();//拿到灯
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("开始执行。。。。。");
						System.out.println("释放前有"+Thread.currentThread().getName()+"  "+(3-semaphore.availablePermits()));
						semaphore.release();//释放灯
						System.out.println("后有"+(3-semaphore.availablePermits()));
					}
				};
				//把执行业务装进线程池里面
				executorService.execute(runnable);
			}
		}
}
