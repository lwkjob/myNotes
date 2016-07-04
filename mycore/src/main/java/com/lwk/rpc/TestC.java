package com.lwk.rpc;

import com.lwk.rpc.service.RpcInterface;

import java.lang.reflect.Proxy;

/**
 * Created by liwenke on 16/5/27.
 */
public class TestC {

    public static void main(String[] args) throws  Exception{
        PerformanceHandler performanceHandler=new PerformanceHandler();
        RpcInterface rpcInterface= (RpcInterface)Proxy.newProxyInstance(TestC.class.getClassLoader(), new Class[]{RpcInterface.class},performanceHandler);
        rpcInterface.helloLwk("121",21);

    }
}
