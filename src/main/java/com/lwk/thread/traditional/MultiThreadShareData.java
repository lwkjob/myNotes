package com.lwk.thread.traditional;
/**
 * 
 * 多个线程数据共享,把数据封装到Runnable对象里面实现共享
 * @author lwkjob
 *
 */
public class MultiThreadShareData {

	public static void main(String[] args) {
		ShareData1 data1 = new ShareData1();
		new Thread(data1).start();
		new Thread(data1).start();
	}

}
	

	/**
	 * 两个线程对同一个数据操作，把数据封装到Runnable对象里面实现共享
	 * @author lwkjob
	 *
	 */
	class ShareData1 implements Runnable{
		private int count = 100;
		@Override
		public void run() {
				while(count>0){
					decrement();
				}
		}
		public synchronized void increment(){
			count++;
		}
		
		public synchronized void decrement(){
			count--;
			System.out.println(Thread.currentThread().getName()+"count"+count);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}