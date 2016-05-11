package com.lwk.thread.pool.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * condition条件
 * 用lock和lock的condition代替synchronized,waite和notify控制线程的互斥和通讯
 * @author lwkjob
 *
 */
public class MultiConditionCommunicationThread {
	public static void main(String[] args) {
		final Business b=new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <=3; i++) {
					b.sub(i);
				}
			}
		}).start();
		
		for (int i = 0; i <=3; i++) {
			b.main(i);
		}
	}
	
}

class Business{
	Lock lock=new ReentrantLock();
	Condition condition=lock.newCondition();
	private boolean flag=true;
	
	
	public void sub(int i){
		lock.lock();
		try{
			while(!flag){//		用while可以防止假唤醒
				waits(condition);
			}
			for (int j = 1; j <= 5; j++) {
				System.out.println("sub 当前位置" + j+"  "+i);
			}
			flag=false;
			condition.signal(); //通知其他等待的线程，目前只有main
		}finally{
			lock.unlock();
		}
	}
	
	public  void main(int i){
		lock.lock();
		try{
		
		while(flag)//		用while可以防止假唤醒
			waits(condition);
		for (int j = 1; j <= 7; j++) {
			System.out.println("main 当前位置" + j+"  "+i);
		}
		flag=true;
		condition.signal();//通知其他等待的线程，目前只有sub
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
