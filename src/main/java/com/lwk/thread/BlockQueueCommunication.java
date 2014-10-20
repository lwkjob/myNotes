package com.lwk.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 *  用阻塞队列实现线程互斥和相互通讯(用queue当同步锁和通知机制呵呵有意思)
 * @author lwkjob
 *
 */
public class BlockQueueCommunication {
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
		
		 
	}
	
}

/**
 * 
 * 专门处理业务逻辑
 * @author lwkjob
 *
 */
class Businesser{
	
	//构建主线程队列
	BlockingQueue<Integer> queueSub1=new ArrayBlockingQueue<Integer>(1);
	//子线程队列
	BlockingQueue<Integer> queueSub2=new ArrayBlockingQueue<Integer>(1);
	{
//		匿名构造方法，每次创建对象都执行，在任何构造方法之前运行
		try {
			queueSub2.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public void sub1(int i){
		try {	
			queueSub1.put(i);
			for (int j = 1; j <= 5; j++) {
				System.out.println("sub1 当前位置" + j+"  "+i);
			}
			queueSub2.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
	}
	public void sub2(int i){
		try{
			queueSub2.put(i);
			for (int j = 1; j <= 3; j++) {
				System.out.println("sub2 当前位置" + j+"  "+i);
			}
			queueSub1.take();
		}catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}
