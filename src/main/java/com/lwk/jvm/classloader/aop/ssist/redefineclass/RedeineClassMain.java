package com.lwk.jvm.classloader.aop.ssist.redefineclass;
import java.io.IOException;

import com.lwk.jvm.classloader.aop.asm.ForASMTestClass;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;


/**
 * 运行
 * java -javaagent:F:\MyGrowth\target\classes\lwk.jar RedeineClassMain
 * @author lwkjob
 *
 */
public class RedeineClassMain {

	public static void main(String []args) throws Exception {
		ForASMTestClass testClass = new ForASMTestClass();
		//修改前
		testClass.display1();//display1name--null
		byte[] bytes = convertByteCode();
		
		//这个修改只影响字节码内部
		InstForRedefineClass.redefineClass(ForASMTestClass.class, bytes);//display2--打印lwk你妹的
		//修改后
		testClass.display1();//display1name--null
		testClass.display1();//display1name--null
		testClass.display1();//display1name--null
		
		
		//再次修改就会失败class is frozen 这个class已经被冻结了
//		byte[] bytes2 = convertByteCode2();
	}

	/**
	 * 
	 * 这个方法只会被调用一次，只能修改异常字节码
	 * @return
	 */
	private static byte[] convertByteCode() throws NotFoundException,
			CannotCompileException, IOException {
		CtClass ctClass = ClassPool.getDefault().get("com.lwk.jvm.classloader.aop.asm.ForASMTestClass");
		CtMethod ctMethod = ctClass.getDeclaredMethod("display1");
		ctMethod.insertBefore("{ System.out.println(\"开始拦截 前面加一条呀！\"); }");
		ctMethod.insertAfter(
				"String a = \"定义个String\";" +
				"name=\"你妹的\";" +
				"value=\"你妹的2\";" +
				"System.out.println(\"拦截结束 输出我定义的String！\" + value+a);"
				+ " display2(); "
		);
		byte[]bytes = ctClass.toBytecode();
		return bytes;
	}
	
}
