package com.lwk.rpc;
import com.lwk.rpc.service.RpcImpl;
import com.lwk.rpc.service.RpcInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * 
 * 输入流和输出流 分开任务
 * @author lwkjob
 *
 */
public class SocketServer {

	public static void main(String []args) throws IOException {
		ServerSocket serSocket = new ServerSocket();
		ObjectOutputStream objectOutputStream;
		serSocket.bind(new InetSocketAddress("127.0.0.1",8888));
		System.out.println("端口已经打开为8888，开始准备接受数据");
		try {
		 Socket socket= serSocket.accept();
			ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
			String interfaceName = input.readUTF(); //接口名称
			String methodName = input.readUTF(); //方法名称
			Class<?>[] parameterType = (Class<?>[]) input.readObject(); //参数类型
			Object[] arguments = (Object[]) input.readObject();    //参数列表
			System.out.println("服务名:"+interfaceName+" 方法:"+methodName+" 参数："+ Arrays.toString(arguments));

			//根据接口名称获取class
			Class<?> serviceInterfaceClass = Class.forName(interfaceName);
			//根据方法名称和参数类型反射得到方法
			Method method = serviceInterfaceClass.getMethod(methodName, parameterType);
			//服务实例化（这里做简单处理，正常应该根据得到的接口名称serviceInterfaceClass获取对应的service,但本demo只提供一个服务）
			RpcInterface service = new RpcImpl();

			//反射执行这个方法
			Object result = method.invoke(service, arguments);

			//写会处理结果
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(result);
		}catch ( Exception e){


		}finally {

		}
		
	}
}
