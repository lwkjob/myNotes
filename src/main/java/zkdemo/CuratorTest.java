package zkdemo;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryLoop;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;

import java.util.concurrent.Callable;

/**
 * Created by tc on 2015/11/24.
 */
public class CuratorTest {



public void retryLoop(){

//    RetryLoop retryLoop = client.newRetryLoop();
//    while ( retryLoop.shouldContinue() )
//    {
//        try
//        {
//            // perform your work
//            ...
//            // it's important to re-get the ZK instance as there may have been an error and the instance was re-created
//            ZooKeeper      zk = client.getZookeeper();
//
//            retryLoop.markComplete();
//        }
//        catch ( Exception e )
//        {
//            retryLoop.takeException(e);
//        }
//    }
}

    public void retryLoop2(){

        CuratorFramework curatorFramework= CuratorFrameworkFactory.newClient("", new RetryPolicy() {
            @Override
            public boolean allowRetry(int i, long l, RetrySleeper retrySleeper) {
                return false;
            }
        });


    //    RetryLoop.callWithRetry(client, new Callable()
    //    {
    //        @Override
    //        public Void call() throws Exception
    //        {
    //            // do your work here - it will get retried if needed
    //            return null;
    //        }
    //    });
    }
}