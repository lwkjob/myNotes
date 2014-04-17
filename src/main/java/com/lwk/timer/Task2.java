package com.lwk.timer;
import java.util.Timer;
import java.util.TimerTask;
public class Task2 {
  public static void main(String[] args) {
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        // task to run goes here
        System.out.println("Hello !!!");
      }
    };
    Timer timer = new Timer();
    long delay = 0;//开始时间
    long intevalPeriod = 1 * 1000;//循环间隔
    // schedules the task to be run in an interval
    timer.scheduleAtFixedRate(task, delay,intevalPeriod);
  } // end of main
}