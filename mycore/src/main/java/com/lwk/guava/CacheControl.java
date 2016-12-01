package com.lwk.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 服务限流
 * http://mp.weixin.qq.com/s?__biz=MzIwODA4NjMwNA==&mid=2652897781&idx=1&sn=ae121ce4c3c37b7158bc9f067fa024c0#rd
 * Created by lwk on 2016/11/3.
 */
public class CacheControl {


    public void control() throws ExecutionException, InterruptedException {
        LoadingCache<Long, AtomicLong> counter =
                CacheBuilder.newBuilder()
                        .expireAfterWrite(2, TimeUnit.SECONDS)
                        .build(new CacheLoader<Long, AtomicLong>() {
                            @Override
                            public AtomicLong load(Long seconds) throws Exception {
                                return new AtomicLong(0);
                            }
                        });

        long limit = 1000;

        while(true) {
            //得到当前秒
            TimeUnit.MILLISECONDS.sleep(500);
            long currentSeconds = System.currentTimeMillis() / 1000;
            if(counter.get(currentSeconds).incrementAndGet() > limit) {
                System.out.println("限流了:" + currentSeconds);
                continue;
            }
            //业务处理
            System.out.println("业务处理");
        }
    }

    public static void main(String[] args) throws Exception {
//        new CacheControl().control();
        new CacheControl().control2();
    }


    public void control2(){
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
    }
}
