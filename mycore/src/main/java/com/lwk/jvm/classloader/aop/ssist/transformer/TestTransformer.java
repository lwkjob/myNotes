package com.lwk.jvm.classloader.aop.ssist.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * 用javassist装载字节码，动态修改后交给虚拟机，这样在环境里调用目标类都是被修改后的了，可以用来实现aop
 * @author lwkjob
 *
 */
public class TestTransformer implements ClassFileTransformer {

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		//输出装载的类名,同样看到了类的装载
		System.out.println("load class:" + className);
		//只有指定的类才装载
		if("com/lwk/jvm/classloader/aop/asm/ForASMTestClass".equals(className)) {
			try {
				CtClass ctClass = ClassPool.getDefault().get(className.replace('/', '.'));
				CtMethod ctMethod = ctClass.getDeclaredMethod("display1");
//				拦截前
				ctMethod.insertBefore(
						"name=\"我是name！这次用javassist了哦！\";" +
						"value=\"我是value！\";" +
						"System.out.println(\"拦截前：\" + name);"
				);
//				拦截后
				ctMethod.insertAfter("System.out.println(\"拦截后\"+value);");
				
				CtMethod ctMethod2 = ctClass.getDeclaredMethod("display2");
				ctMethod2.insertBefore("System.out.println(\"lwk拦截：----------------------------------\");"
										+ "display1();");
				ctMethod2.insertAfter("System.out.println(\"lwk拦截后：悄悄的离开----------------------------------\");");
				
				//返回修改后的字节码
				return ctClass.toBytecode();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return classfileBuffer;
	}
}
