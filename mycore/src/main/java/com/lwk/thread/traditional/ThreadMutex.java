package com.lwk.thread.traditional;

/**
 * 线程互斥	synchronized
 * @author lwkjob
 * output1同步方块用的锁和output2,output3的不是同一把锁所以不能互斥，只有2,3能互斥
 * output1同步方块用的锁是对象本身所以output1和output4是可以互斥的
 * 内部类可以访问外部类的成员变量
 */
public class ThreadMutex {
	
	public static void main(String[] args) {
		final OutPuter printer=new OutPuter();
		 new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					Thread.yield();
					printer.output1("liwenke");
				}
			}
		}).start();
		 
		 new Thread(new Runnable() {
			 @Override
			 public void run() {
				 while(true){
					 printer.output3("ganhongzhen");
					 Thread.yield();
				 }
			 }
		 }).start();

	}
}

class OutPuter{
	//一个字母一个字母的打印 synchronized保障互斥
	
	public synchronized void output1(String name){
		for (int i = 0; i < name.length(); i++) {
			System.out.print(name.charAt(i));
		}
		System.out.println();
	}
	
	/**
	 * 静态方法 同步的时候用的同步锁是这个类的字节码对象OutPuter.class
	 * @param name
	 */
	public static synchronized void output2(String name){
		for (int i = 0; i < name.length(); i++) {
			System.out.print(name.charAt(i));
		}
		System.out.println();
	}
	/**
	 * 同步代码块
	 * @param name
	 */
	public  void output3(String name){
		synchronized (OutPuter.class) {
			for (int i = 0; i < name.length(); i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}
	/**
	 * 同步代码块用对象本身做锁
	 * @param name
	 */
	public  void output4(String name){
		synchronized (this) {
			for (int i = 0; i < name.length(); i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}
}
