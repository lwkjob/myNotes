package com.lwk.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {
	
	public static void main(String[] args) {
		class MyTask extends TimerTask{
			@Override
			public void run() {
				System.out.println("我是一个任务");
				new Timer().schedule(new MyTask(), 1000,1000);
			}
			
		}
		 new Timer().schedule(new MyTask(),1000, 1000);
		
		//
		while(true){
			System.out.println(new Date().getSeconds());
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
		}
	}
}
