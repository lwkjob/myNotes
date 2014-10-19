package com.lwk.thread.pool;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 传统的线程和定时任务类
 * 
 * @author lwkjob
 */
public class TraditionalThread {
	public static void main(String[] args) {
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"runnable");	
				
			}
		}){
			//重写父类方法(优先执行)
			public void run() {
				System.out.println(this.getName()+"我是thread");	
			};
		}.start();
		
		
		
		int startTime=1000;//开始时间
		int perTime=1000;//每单位时间
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("定时器开始任务"+new Date().getSeconds());
			}
		}, startTime,perTime);
		
		new Timer().schedule(new BombTimerTask(),0);
		
	}
	
}

class BombTimerTask extends TimerTask{
	static int count=0;
		@Override
		public void run() {
			count=(count+1)%2;
			System.out.println("自定义bomb的TimerTask");
			//每三秒或者五秒调用自己
			new Timer().schedule(new BombTimerTask(), 2000+count*3000);
		}
}
