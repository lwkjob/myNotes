package com.lwk.thread;

/**
 * @author lwk
 * 线程通信communication 
 * 子线程循环5次，然后主线程循环7次  如此一次循环3次
 */
public class TraditionalCommuniction {
	public static void main(String[] args) {
		final Business b=new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <=3; i++) {
					b.sub();
				}
			}
		}).start();
		
		for (int i = 0; i <=3; i++) {
			b.main();
		}
	}
	
}

class Business{
	private boolean flag=true;
	public synchronized void sub(){
		if(!flag)
			waits();
		for (int j = 1; j <= 5; j++) {
			System.out.println("sub 当前位置" + j);
		}
		flag=false;
		notify();
	}
	
	public  synchronized void main(){
		if(flag)
			waits();
		for (int j = 1; j <= 7; j++) {
			System.out.println("main 当前位置" + j);
		}
		flag=true;
		notify();
	}
	
	//等待
	private void waits() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}













