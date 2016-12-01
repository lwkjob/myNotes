package com.lwk.jvm.classloader.loadClass;

public class B extends A {

	private final static B instance=new B();

	public static B getInstance(){
		return  instance;
	}
	
	@Override
	public void test() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		instance.test2();//当前类的引用。会导致加载失败错误 NoClassDefFoundError
//		test2();
	}
	
	public void test2(){}

}
