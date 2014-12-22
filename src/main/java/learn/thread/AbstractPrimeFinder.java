package learn.thread;


/**
 * 查找素数 计算密集型举例
 * @author lwkjob
 *
 */
public abstract class AbstractPrimeFinder {
	
	/**
	 * 计算
	 * @param number
	 * @return
	 */
	public boolean isPrime(final int number){
		if(number<=1) return false;
		for(int i=2;i<=Math.sqrt(number);i++){
			if(number%i==0) return false;
		}
		return true;
	}
	
	/**
	 * 统计
	 * @param lower
	 * @param upper
	 * @return
	 */
	public int countPrimeRange(final int lower,final int upper){
		int total=0;
		for(int i=lower;i<upper;i++){
			if(isPrime(i)){
				total++;
			}
		}
		return total;
	}
	
	/**
	 * 查看时间
	 * @param number
	 */
	public void timeAndCompute(final int number){
		final long start=System.nanoTime();
		final long numberOfPrime=countPrime(number);
		final long end=System.nanoTime();
		System.out.println(String.format("%d %d\n",number,numberOfPrime));
		System.out.println("执行时间"+(end-start)/1.0e9);
	}

	public abstract int countPrime(int number);
}
