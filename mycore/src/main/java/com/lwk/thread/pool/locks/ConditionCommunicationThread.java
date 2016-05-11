package com.lwk.thread.pool.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 多个condition条件之间实现有顺序的通知(阻塞队列)
 * 用lock和lock的condition代替synchronized,waite和notify控制线程的互斥和通讯
 * @author lwkjob
 *
 */
public class ConditionCommunicationThread {
	public static void main(String[] args) {
		final Businesser b=new Businesser();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <=3; i++) {
					b.sub1(i);
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <=3; i++) {
					b.sub2(i);
				}
			}
		}).start();
		
		for (int i = 0; i <=3; i++) {
			b.sub3(i);
		}
	}
	
}

/**
 * 
 * 专门处理业务逻辑
 * @author lwkjob
 *
 */
class Businesser{
	Lock lock=new ReentrantLock();
	Condition condition1=lock.newCondition();
	Condition condition2=lock.newCondition();
	Condition condition3=lock.newCondition();
	private int flag=1;
	
	public void sub1(int i){
		lock.lock();
		try{
			while(flag!=1){//		用while可以防止假唤醒
				waits(condition1); 	//老大睡觉
			}
			for (int j = 1; j <= 5; j++) {
				System.out.println("sub1 当前位置" + j+"  "+i);
			}
			flag=2;
			condition2.signal(); //通知老2
		}finally{
			lock.unlock();
		}
	}
	public void sub2(int i){
		lock.lock();
		try{
			while(flag!=2){//		用while可以防止假唤醒
				waits(condition2); //老2睡觉
			}
			for (int j = 1; j <= 3; j++) {
				System.out.println("sub2 当前位置" + j+"  "+i);
			}
			flag=3;
			condition3.signal(); //通知老3
		}finally{
			lock.unlock();
		}
	}
	public void sub3(int i){
		lock.lock();
		try{
			while(flag!=3){//		用while可以防止假唤醒
				waits(condition3); //老3睡觉
			}
			for (int j = 1; j <= 2; j++) {
				System.out.println("sub3 当前位置" + j+"  "+i);
			}
			flag=1;
			condition1.signal(); //通知老1
		}finally{
			lock.unlock();
		}
	}
	
	
	//等待
	private void waits(Condition condition) {
		try {
			condition.await();//注意这里是await不是wait
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
