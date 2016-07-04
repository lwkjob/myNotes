package com.lwk.rpc.context;


import com.lwk.rpc.PerformanceHandler;
import com.lwk.rpc.service.RpcInterface;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liwenke on 16/5/27.
 */
public class ContextTest {

    public static void main(String[] args) throws Exception {
        ContextTest contextTest=new ContextTest();
        contextTest.test();
    }

    public void test() throws Exception{
        Map<String, Object> context = new HashMap<>();
        Object o = Class.forName("com.lwk.rpc.context.ClientTest").newInstance();
        Field rpcInterfaceField = o.getClass().getDeclaredField("rpcInterface");


        PerformanceHandler performanceHandler = new PerformanceHandler();
        Object rpcInterface = Proxy.newProxyInstance(
                ContextTest.class.getClassLoader(),
                new Class[]{RpcInterface.class},
                performanceHandler);

        rpcInterfaceField.setAccessible(true);
        rpcInterfaceField.set(o, rpcInterface);
        context.put("clientTest", o);

        ClientTest clientTest= (ClientTest)context.get("clientTest");
        clientTest.mm();
    }
}
