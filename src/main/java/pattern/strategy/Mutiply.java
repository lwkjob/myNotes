package pattern.strategy;

public class Mutiply implements Caculator {
	@Override
	public int simpleCaculator(int numOne, int numTwo) {
		return numOne*numTwo;
	}
}
