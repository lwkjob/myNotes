package pattern.strategy;

/**
 * @author lwk
 * 加法实现
 */
public class Plus implements Caculator{

	@Override
	public int simpleCaculator(int numOne, int numTwo) {
		return numOne+numTwo;
	}

}
