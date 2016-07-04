//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package zkdemo.zk.core.manager;

import zkdemo.zk.core.config.ZookeeperConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class CuratorManagerFramework {
    private static final Map<ZookeeperConfig, CuratorManager> CURATORMANAGERPOOL = new ConcurrentHashMap();
    private static final Lock LOCK = new ReentrantLock();

    public CuratorManagerFramework() {
    }

    public static CuratorManager build(ZookeeperConfig config) {
        Lock currentLock = LOCK;

        CuratorManager var3;
        try {
            CuratorManager curatorManager = (CuratorManager)CURATORMANAGERPOOL.get(config);
            currentLock.lock();
            if(null == curatorManager) {
                curatorManager = new CuratorManager(config);
                CURATORMANAGERPOOL.put(config, curatorManager);
            }

            var3 = curatorManager;
        } finally {
            currentLock.unlock();
        }

        return var3;
    }

    public static void destory(ZookeeperConfig config) {
        Lock currentLock = LOCK;
        currentLock.lock();

        try {
            CuratorManager curatorManager = (CuratorManager)CURATORMANAGERPOOL.remove(config);
            if(null != curatorManager) {
                curatorManager.close();
            }
        } finally {
            currentLock.unlock();
        }

    }
}
