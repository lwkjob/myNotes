package com.lwk.thread;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {
	public static void main(String[] args) {
		
		final ConcurrentLinkedQueue<String> mm=new ConcurrentLinkedQueue<String>();
		
		Runnable putRun= new Runnable() {
			public void run() {
				int i=10;
				while(i>0){
					System.out.println("生产 lwk and ganzhe "+i);
					mm.add("lwk and ganzhe "+i);
					i--;
				}
				System.out.println("数据放完了"+mm.size());
			}
		};
		Runnable getRun= new Runnable() {
			public void run() {
				while(true){
					String mstr=mm.poll();
					if(mstr!=null){
						System.out.println("消费	"+mstr);
					}
				}
			}
		};
		new Thread(putRun).start();
		new Thread(getRun).start();
	}
}
