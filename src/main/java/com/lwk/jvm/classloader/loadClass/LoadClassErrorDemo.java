package com.lwk.jvm.classloader.loadClass;

/**
 * 
 * 类加载的时候,static代码引用了类的引用会报错,NoClassDefFoundError
 * @author lwkjob
 *
 */
public class LoadClassErrorDemo {
	public static void main(String[] args) {
		new Thread(){
			public void run() {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				B.getInstance().test2();
			};
		}.start();
		new Thread(){
			public void run() {
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				B.getInstance().test2();
			};
		}.start();
	}
}
