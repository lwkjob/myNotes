package com.test;

import java.util.concurrent.TimeUnit;

/**
 * Created by liwenke on 16/5/19.
 */
public class Test {

    public static void main(String[] args) throws Exception{
        new  Test2().testVerbose();
        System.out.println("哈喽你好啊!!!!");
        TimeUnit.SECONDS.sleep(5);
        new Test3().test3();
    }
}
