package learn.thread;

public class SequentialPrimeFinder extends AbstractPrimeFinder{
	
	public int countPrime(final int number){
		return countPrimeRange(1, number);
	}
	public static void main(String[] args) {
		new SequentialPrimeFinder().timeAndCompute(10000000);
	}

}
