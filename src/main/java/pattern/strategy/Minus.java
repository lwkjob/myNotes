package pattern.strategy;

/**
 * @author lwk
 * 减法  [ˈmaɪnəs]
 */
public class Minus implements Caculator{
	@Override
	public int simpleCaculator(int numOne, int numTwo) {
		//取个绝对值
		return Math.abs(numOne-numTwo);
	}
}
