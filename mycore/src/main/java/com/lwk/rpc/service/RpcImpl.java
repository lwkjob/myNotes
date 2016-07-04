package com.lwk.rpc.service;

import com.lwk.rpc.service.RpcInterface;

/**
 * Created by liwenke on 16/5/25.
 */
public class RpcImpl implements RpcInterface {
    @Override
    public String helloLwk(String msg,Integer count) {
        return "服务端返回:"+msg+" "+count;
    }
}
