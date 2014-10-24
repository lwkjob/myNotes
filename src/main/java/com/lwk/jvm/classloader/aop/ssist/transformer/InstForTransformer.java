package com.lwk.jvm.classloader.aop.ssist.transformer;
import java.lang.instrument.Instrumentation;

/**
 * 
Manifest-Version: 1.0
Created-By: 1.6.0_23 (Sun Microsystems Inc.)
Premain-Class: com.lwk.jvm.classloader.aop.ssist.transformer.InstForTransformer
Class-Path: .

 * 将transformer注册到instrumentation
 * @author lwkjob
 *
 */
public class InstForTransformer {
	
    private static Instrumentation inst;
	
    /*编译为agent后，系统启动执行main方法前会调用它*/
    public static void premain(String agentArgs, Instrumentation instP) {
    	inst=instP;
    	inst.addTransformer(new TestTransformer());
    }
    
}
