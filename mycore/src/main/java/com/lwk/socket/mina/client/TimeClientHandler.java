package com.lwk.socket.mina.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

class TimeClientHandler extends IoHandlerAdapter {
    public TimeClientHandler() {
        //原来如此
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println(message);// 显示接收到的消息
    }
}