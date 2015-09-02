package com.lwk.timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class Task3 {
  public static void main(String[] args) {
    Runnable runnable = new Runnable() {
      public void run() {
        // task to run goes here
    	  try {
			Thread.sleep(3*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Hello !!");
      }
    };
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//    安排指定的任务在指定的延迟后开始进行重复的固定速率执行．
    service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    System.out.println("1212");
    service.shutdown();
    System.out.println("22222");
    System.out.println(service.isShutdown());
  }
}
