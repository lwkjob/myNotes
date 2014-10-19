package com.lwk.thread.pool.locks;


import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 读锁与读锁不互斥，写锁与写锁互斥，写锁与读锁互斥
 * @author lwkjob
 *
 */
public class ReadWriteLockTest {
	
	public static void main(String[] args) {
		
		final Queue3 q3 = new Queue3();
		for(int i=0;i<3;i++)
		{
			new Thread(){
				public void run(){
					while(true){
						q3.get();						
					}
				}
				
			}.start();

			new Thread(){
				public void run(){
					while(true){
						q3.put(new Date().getTime());
					}
				}			
				
			}.start();
		}
		
	}
}

class Queue3{
	//共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
	private Object data = null;
	ReadWriteLock rwl = new ReentrantReadWriteLock();
	public void get(){
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 准备读");
			Thread.sleep(100);
			System.out.println(Thread.currentThread().getName() + "读完了" + data);			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}
	}
	
	public void put(Object data){

		rwl.writeLock().lock();
		try {
//			System.out.println(Thread.currentThread().getName() + " 准备写");					
			Thread.sleep(10000);
			this.data = data;		
//			System.out.println(Thread.currentThread().getName() + " 写完了 " + data);					
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.writeLock().unlock();
		}
	}
}
