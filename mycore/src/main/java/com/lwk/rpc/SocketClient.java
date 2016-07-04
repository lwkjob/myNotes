package com.lwk.rpc;

import com.lwk.proxy.*;
import com.lwk.rpc.service.RpcInterface;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Scanner;

/**
 * 输入流和输出流 分开任务
 *
 * @author lwkjob
 */
public class SocketClient {

    public static void main(String[] args) {
        try {
            //接口名称
            String interfaceName = RpcInterface.class.getName();

            //接口方法
            String methodName = "helloLwk";
//			Method method = RpcInterface.class.getMethod("helloLwk", java.lang.String.class,java.lang.Integer.class);

//			参数类型
            Class<?>[] paramTypes = new Class<?>[]{java.lang.String.class, java.lang.Integer.class};

            //参数
            Object[] arguments = {"hello lwk", 121};

            Socket socket = new Socket("127.0.0.1", 8888);

            //发送请求
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeUTF(interfaceName);
            out.writeUTF(methodName);
            out.writeObject(paramTypes);
            out.writeObject(arguments);

            //获取结果
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object returnObject = inputStream.readObject();
            System.out.println(returnObject.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}