package com.lwk.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class PerformanceHandler implements InvocationHandler {
        //①实现InvocationHandler


    public Object invoke(Object proxy, Method method, Object[] args) //③
            throws Throwable {
        System.out.println("方法名字 = " + method.getName());
        System.out.println("参数 = " + Arrays.asList(args));
        return method.getReturnType().newInstance();
    }
}