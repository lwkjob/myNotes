package com.lwk.jvm.classloader.aop.ssist.redefineclass;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * 运行是注册
Manifest-Version: 1.0
Created-By: 1.6.0_23 (Sun Microsystems Inc.)
Premain-Class: com.lwk.jvm.classloader.aop.ssist.redefineclass.InstForRedefineClass
Can-Redefine-Classes: true
Class-Path: .
 * 
 * @author lwkjob
 *
 */
public class InstForRedefineClass {

	private static Instrumentation inst;

	public static void premain(String agentArgs, Instrumentation instP) {
		inst = instP;
	}

	public static void redefineClass(Class<?> theClass, byte[] theClassFile)
			throws ClassNotFoundException, UnmodifiableClassException {
		inst.redefineClasses(new ClassDefinition(theClass, theClassFile));
	}
}