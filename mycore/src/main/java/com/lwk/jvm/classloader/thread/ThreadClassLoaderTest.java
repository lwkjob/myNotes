package com.lwk.jvm.classloader.thread;

import org.junit.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by lwk on 2016/12/16.
 */
public class ThreadClassLoaderTest {


    @Test
    public void testClassLoader() {

        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
    }


    @Test
    public void testClassLoader2() {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class, ClassLoader.getSystemClassLoader().getParent());
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("system loader:" + ClassLoader.getSystemClassLoader());
        System.out.println("system loader's parent:" + ClassLoader.getSystemClassLoader().getParent());
    }
}
