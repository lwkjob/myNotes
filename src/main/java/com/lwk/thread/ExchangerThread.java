package com.lwk.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 两个线程交换数据
 * @author lwkjob
 *
 */
public class ExchangerThread {
	
	public static void main(String[] args) {
		ExecutorService exService=Executors.newCachedThreadPool();
		final Exchanger<String> exchanger=new Exchanger<String>();
		exService.execute(new Runnable() {
			@Override
			public void run() {
				String mydata="lwk";
				System.out.println(Thread.currentThread().getName()+"交换数据"+mydata);
				try {
					String getData=exchanger.exchange(mydata);
					System.out.println(Thread.currentThread().getName()+"收到数据"+getData);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		exService.execute(new Runnable() {
			@Override
			public void run() {
				String mydata="ganhongzhen";
				System.out.println(Thread.currentThread().getName()+"交换数据"+mydata);
				try {
					String getData=exchanger.exchange(mydata);
					System.out.println(Thread.currentThread().getName()+"收到数据"+getData);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		exService.shutdown();
		
	}
}
