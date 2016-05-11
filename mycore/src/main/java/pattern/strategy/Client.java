package pattern.strategy;

/**
 * @author lwk
 * 策略模式 封装返回向东类型值的不同算法
 */
public class Client {

	public static void main(String[] args) {
		Caculator plus = new Plus();
		Mutiply muti = new Mutiply();
		Minus minus = new Minus();
		
		int result = plus.simpleCaculator(1, 3);
		System.out.println("加法的值:" + result);
		
		result = muti.simpleCaculator(1, 3);
		System.out.println("乘法的值:" + result);
		
		result = minus.simpleCaculator(1, 3);
		System.out.println("减法的值:" + result);
	}
}
