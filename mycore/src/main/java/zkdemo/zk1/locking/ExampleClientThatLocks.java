
package zkdemo.zk1.locking;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import java.util.concurrent.TimeUnit;

/*业务逻辑处理类，拿到锁就开始工作*/
public class ExampleClientThatLocks
{
    private final InterProcessMutex lock;//互斥锁
    private final FakeLimitedResource resource;
    private final String clientName;

    public ExampleClientThatLocks(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName)
    {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessMutex(client, lockPath);
    }

    public void     doWork(long time, TimeUnit unit) throws Exception
    {
        if ( !lock.acquire(time, unit) )//获得锁
        {
            throw new IllegalStateException(clientName + " 没有拿到锁，再等下....");
        }
        try
        {
            System.out.println(clientName + " 我拿到锁了，开始工作......");
            resource.use();
            TimeUnit.SECONDS.sleep(4);
        }
        finally
        {
            System.out.println(clientName + " 工作完成 放开锁");
            lock.release(); // always release the lock in a finally block
        }
    }
}
