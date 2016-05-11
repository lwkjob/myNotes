package com.lwk.jvm.classloader;

/**
 * 类加载器顺序,委托机制(为了保障一份class文件只被加载一次，所以所有的类在加载的时候由父级开始加载)
 * BootStrap
 * ExtClassLoader
 * AppClassLoader
 * @author lwkjob
 * 如果把此类导出到jre的lib目录下的ext目录下，就会被extclassloader所加载
 */
public class ClassLoaderTest {
	public static void main(String[] args) {
		ClassLoader classLoader=ClassLoaderTest.class.getClassLoader();
		while(classLoader!=null){
			// 循环打印此类的加载器树
			System.out.println(classLoader.getClass().getName());
			classLoader=classLoader.getParent();
		}
		System.out.println(classLoader);
			
	}
}
