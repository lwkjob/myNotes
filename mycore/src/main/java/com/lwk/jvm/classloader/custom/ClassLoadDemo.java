package com.lwk.jvm.classloader.custom;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoadDemo{

    public static void main(String[] args) throws Exception {

        ClassLoader clazzLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String clazzName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                    InputStream is = getClass().getResourceAsStream(clazzName);
                    System.out.println("加载:"+name);
                    if (is == null) {
                        //加载当前类的相关类

                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        String currentClass = "com.lwk.jvm.classloader.custom.ClassLoadDemo";
        Class<?> clazz = clazzLoader.loadClass(currentClass);
        System.out.println("加载完成");
        Object obj = clazz.newInstance();
        System.out.println("实例化完成");
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.lwk.jvm.classloader.custom.ClassLoadDemo);
    }

    public void  test(){
        System.out.println("哈喽");
    }
}