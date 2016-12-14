package com.lwk.jvm.classloader.custom.net.data;

import com.lwk.jvm.classloader.custom.net.ICalculator;

public class CalculatorBasic implements ICalculator {

	public String calculate(String expression) {
		return expression;
	}

	public String getVersion() {
		return "1.0";
	}

}