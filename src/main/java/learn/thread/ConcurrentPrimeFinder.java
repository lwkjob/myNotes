package learn.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 多线程 寻找素数
 * @author lwkjob
 *
 */
public class ConcurrentPrimeFinder extends AbstractPrimeFinder{
	
	private final int poolSize; //线程大小
	private final int numberOfParts;//把数分成几份计算
	
	public ConcurrentPrimeFinder(final int poolSize,final int numberOfParts) {
		 this.poolSize=poolSize;
		 this.numberOfParts=numberOfParts;
	}

	@Override
	public int countPrime(int number) {
		int count=0;
		//搞一个任务列表
		final List<Callable<Integer>> parttions=new ArrayList<Callable<Integer>>();
		final int chunksPerParttion=number/numberOfParts;
		
		for(int i=0;i<numberOfParts;i++){
			 final int lower=(i*chunksPerParttion)+1;
			 final int upper=(i==numberOfParts-1)?lower+number:i*chunksPerParttion-1;
			 parttions.add(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return countPrimeRange(lower, upper);
					
				}
			});
		 }
		 
		 //启动线程池执行任务
		 final ExecutorService executorsPool= Executors.newFixedThreadPool(poolSize);
		try {
			final List<Future<Integer>> resultList = executorsPool.invokeAll(parttions,1000,TimeUnit.SECONDS);
			//取任务结果
			 
			 for (final Future<Integer> result:resultList) {
				 count=count+result.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 executorsPool.shutdown();
		 
		 
		return count;
	}
	
	public static void main(String[] args) {
		new ConcurrentPrimeFinder(2, 2).timeAndCompute(10000000);
	}
}
