package com.lwk.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列的其中一个实现类
 * @author lwkjob
 *
 */
public class BlockQueueArray {
	public static void main(String[] args) {
		//创建一个可以放三个数据的阻塞队列
		final BlockingQueue<String> queue=new ArrayBlockingQueue<String>(3);
		for (int i = 0; i < 2; i++) {
			//起2个线程方数据往队列里面放数据
			new Thread(){
				public void run() {
					while(true){
						try {
							 //随机休息时间
							Thread.sleep((long)(Math.random()*100));
							queue.put("lwk");
							System.out.println(Thread.currentThread().getName()+"放好数据了现在有 "+queue.size());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
			}.start();
		}
		//开一个线程取数据
		new Thread(){
			public void run() {
				while(true){
					try {
					 //随机休息时间
					Thread.sleep((long)(Math.random()*1000));
					String getData=	queue.take();
						System.out.println(Thread.currentThread().getName()+"取到数据"+getData+" 现在还有"+queue.size());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		
	}
}
