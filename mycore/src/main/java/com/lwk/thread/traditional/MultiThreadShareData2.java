package com.lwk.thread.traditional;
/**
 * 
 * 多个线程数据共享,把数据放到类的成员变量然后让runnable去取，实现共享
 * @author lwkjob
 *
 */
public class MultiThreadShareData2 {
	
	private int count=100;
	
	public static void main(String[] args) {
		MultiThreadShareData2 mts2=new MultiThreadShareData2();
		
//		new内部类的对象要用用外部类对象点new 
//		用同一个外部类对象new出来的内部类对象共享同一份成员变量数据
		Runnable inr=mts2.new Income();
		Runnable der=mts2.new Decome();
		new Thread(der).start();
		new Thread(inr).start();
	}
	
	private synchronized void increment(){
		count++;
		System.out.println("+++++"+count);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void decrement(){
		count--;
		System.out.println("----"+count);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	class Income implements Runnable{
		@Override
		public void run() {
			for (int i = 0; i <30; i++) {
				increment();
			}
		}
		
	}
	
	class Decome implements Runnable{
		@Override
		public void run() {
			for (int i = 0; i <20; i++) {
				decrement();
			}
		}
	}

}


	