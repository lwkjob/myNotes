package zkdemo.zk1.leader;
  
import org.apache.curator.RetryPolicy;  
import org.apache.curator.framework.CuratorFramework;  
import org.apache.curator.framework.CuratorFrameworkFactory;  
import org.apache.curator.framework.recipes.leader.LeaderLatch;  
import org.apache.curator.retry.ExponentialBackoffRetry;
import pattern.adapter.Source;

import java.util.concurrent.TimeUnit;

public class LeaderLatchDemo {  
  
    public static void main(String[] args) throws Exception {  
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").sessionTimeoutMs(2000).connectionTimeoutMs(10000).retryPolicy(retryPolicy).namespace("text").build();
        client.start();
        // 选举Leader 启动
        LeaderLatch latch = new LeaderLatch(client,"/path");
        latch.start();
        latch.await();
        System.out.println("我启动了，开干");
        TimeUnit.SECONDS.sleep(10);
        System.out.println("我干完了你们继续。。。。。");
        latch.close();
        client.close();
    }
} 