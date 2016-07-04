package com.lwk.rpc.context;

import com.lwk.rpc.service.RpcInterface;

/**
 * Created by liwenke on 16/5/27.
 */
public class ClientTest {

    private RpcInterface rpcInterface;

    public void mm(){
        rpcInterface.helloLwk("李文科你好",212);
    }

    public RpcInterface getRpcInterface() {
        return rpcInterface;
    }

    public void setRpcInterface(RpcInterface rpcInterface) {
        this.rpcInterface = rpcInterface;
    }
}
