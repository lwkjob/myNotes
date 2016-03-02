package com.lwk.thread.pool.locks;


import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
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

			new Thread(()-> {
				while(true){
					q3.get();

				}
			},"lwk_____read"+i).start();

			new Thread(()-> {
				while(true){
					q3.put(new Date().getTime());

				}
			},"lwk_____write"+i).start();




		}
		
	}
}

class Queue3{
	//共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
	private  List<String> data = new CopyOnWriteArrayList<>();

	ReadWriteLock rwl = new ReentrantReadWriteLock(true);//设置为公平锁

	public void get(){
		try {
			rwl.readLock().lock();
			System.out.println(Thread.currentThread().getName() + " 准备读");
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + "读完了" + Joiner.on(",").join(this.data));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}
	}
	
	public void put(Object data){

		try {
			rwl.writeLock().lock();
			System.out.println(Thread.currentThread().getName() + " 准备写");
			Thread.sleep(1000);
			this.data.add(data+"");
			System.out.println(Thread.currentThread().getName() + " 写完了 ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.writeLock().unlock();
		}
	}
}
