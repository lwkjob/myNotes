package com.lwk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class PerformanceHandler implements InvocationHandler {//①实现InvocationHandler
    private Object target;

    public PerformanceHandler(Object target) { //②target为目标的业务类
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) //③
            throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName() + "." + method.getName());//③-1
        Object obj = method.invoke(target, args);// ③-2通过反射方法调用业务类的目标方法
        PerformanceMonitor.end();//③-1
        return obj;
    }
}