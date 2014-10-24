package com.lwk.jvm.classloader.aop.asm;

public class ForASMTestClass {
	
	private String name;
	private String value;
	
	public void display1(){
		
		System.out.println("display1name--"+name);
		System.out.println("display1value--"+value);
	}
	
	public void display2(){
		System.out.println("display2--"+"打印lwk"+name);
	};
}
