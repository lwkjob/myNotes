package com.lwk.jvm.classloader.aop.ssist.transformer;
import java.lang.instrument.UnmodifiableClassException;

import com.lwk.jvm.classloader.aop.asm.ForASMTestClass;


/**
 * 利用instrument
 * -javaagent:F:\MyGrowth\target\classes\lwk.jar
 * @author lwkjob
 *
 */
public class TestformerTestMain {
	
	public static void main(String []args) throws UnmodifiableClassException {
		ForASMTestClass testClass = new ForASMTestClass();
		testClass.display1();
		testClass.display2();
	}
}
