package com.lwk.jvm.classloader.custom.net;

/**
 *
 * URL[] baseUrls = {new URL("file:/d:/testLib/")};
 *
 * https://www.ibm.com/developerworks/cn/java/j-lo-classloader/
 *
 */
public class CalculatorTest {

	public static void main(String[] args) {
		String url = "http://localhost:8080/ClassloaderTest/classes";

		NetworkClassLoader ncl = new NetworkClassLoader(url);
		String basicClassName = "com.lwk.jvm.classloader.custom.net.CalculatorBasic";
		String advancedClassName = "com.lwk.jvm.classloader.custom.net.CalculatorAdvanced";
		try {
			Class<?> clazz = ncl.loadClass(basicClassName);
			ICalculator calculator = (ICalculator) clazz.newInstance();
			System.out.println(calculator.getVersion());

			clazz = ncl.loadClass(advancedClassName);
			calculator = (ICalculator) clazz.newInstance();

			System.out.println(calculator.getVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}