package com.lwk.thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 用信号灯semaphore控制互斥，只有拿到灯的线程才可以执行
 * 
 * @author lwkjob
 * 
 */
public class SemaphoreDemo {
	public static void main(String[] args) throws InterruptedException {

		final Pool pool = new Pool();

		ExecutorService executorService = Executors.newCachedThreadPool();
		// 100个线程去拿100个资源但是只有10并发个通行证
		for (int i = 0; i < 100; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					String mm = pool.getR();
					// 模拟业务处理时间
					sleep();
					pool.putR(mm);
				}
			};
			// 把执行业务装进线程池里面
			executorService.execute(runnable);
			}
	}

	public static void sleep() {
		try {
			// 睡觉
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
		public static class Pool{
			//资源池
			ArrayList<String> pool=null;
			//通行证
			Semaphore pass=null;
			//锁(锁)
			Lock lock=new ReentrantLock();
			
			public Pool(){
				
				pool=new ArrayList<String>();
				//初始化100个资源
				for (int i = 0; i < 100; i++) {
					pool.add(i+"资源");
				}
				//只有2个通行证
				pass=new Semaphore(10);
			}
			
			public String getR(){
				try {
					pass.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}//拿到通行证
				lock.lock();
				System.out.println(Thread.currentThread().getName()+" 拿到通行证");
				String ret=pool.get(0);
				pool.remove(0);
				System.out.println(Thread.currentThread().getName()+" 拿走了 "+ret);
				lock.unlock();
				return ret;
			}
			
			
			public void putR(String r){
				pass.release();//释放通行证
				lock.lock();
				System.out.println(Thread.currentThread().getName()+" 释放通行证");
				pool.add(r);
				System.out.println(Thread.currentThread().getName()+" 归还 "+r);
				lock.unlock();
			}
		}
}
