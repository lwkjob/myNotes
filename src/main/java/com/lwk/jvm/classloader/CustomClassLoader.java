package com.lwk.jvm.classloader;

/**
 * 自定义classloader
 * @author lwkjob
 *
 */
public class CustomClassLoader extends ClassLoader {

	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println(name);
		return super.findClass(name);
	}
}
