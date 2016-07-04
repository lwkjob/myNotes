
package zkdemo.zk1.locking;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Simulates some external resource that can only be access by one process at a time
 */
public class FakeLimitedResource
{
    private final AtomicBoolean     inUse = new AtomicBoolean(false);

    public void     use() throws InterruptedException
    {
        // in a real application this would be accessing/manipulating a shared resource

        if ( !inUse.compareAndSet(false, true) )
        {
            throw new IllegalStateException("锁正在被使用等一下");
        }

        try
        {
            Thread.sleep((long)(3 * Math.random()));
        }
        finally
        {
            inUse.set(false);
        }
    }
}
