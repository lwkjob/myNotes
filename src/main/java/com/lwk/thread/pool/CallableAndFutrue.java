package com.lwk.thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 
 * 有返回值的多线程返回值装在Futrue里面
 * @author lwkjob
 *
 */
public class CallableAndFutrue {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService threadPool=Executors.newSingleThreadExecutor();
		Future<String>	future=threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "hello lwk";
			}
		});
		System.out.println("提交了");
		System.out.println("拿到结果"+future.get());
		
		//固定大小的线程池
		ExecutorService threadPool2=Executors.newFixedThreadPool(10);
		//提交一组任务等待结果
		CompletionService<String> completionService= new ExecutorCompletionService<String>(threadPool2);
		for (int i = 0; i < 10; i++) {
			final int n=i;
			completionService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					Thread.sleep(1000*10);//故意执行很久，看取不到值会不会阻塞
					return "第"+n;
				}
			});
		}
		System.out.println("提交完了");
		//取
		for (int i = 0; i < 10; i++) {
			//如果取不到值会一直阻塞。。。。
			Future<String> retFuture=completionService.take();
			System.out.println(retFuture.get());
		}
		
	}
}
