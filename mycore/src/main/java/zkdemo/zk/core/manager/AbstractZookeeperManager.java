//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package zkdemo.zk.core.manager;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import zkdemo.zk.core.listener.ZookeeperStateListener;
import zkdemo.zk.core.listener.ZookeeperStateListener.State;
import zkdemo.zk.core.manager.ZookeeperManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractZookeeperManager implements ZookeeperManager {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractZookeeperManager.class);
    private final Lock nodeLock = new ReentrantLock();
    private final Lock pathChildrenLock = new ReentrantLock();
    private final Map<String, NodeCache> nodeCachePool = new ConcurrentHashMap();
    private final Map<String, PathChildrenCache> pathChildrenCachePool = new ConcurrentHashMap();
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final Set<ZookeeperStateListener> stateListenerPool = new ConcurrentSkipListSet();

    public AbstractZookeeperManager() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                AbstractZookeeperManager.this.close();
            }
        });
    }

    public void create(String path, boolean isTempNode) {
        int i = StringUtils.lastIndexOf(path, 47);
        if(i > 0) {
            this.create(StringUtils.substring(path, 0, i), false);
        }

        if(!this.checkExist(path)) {
            if(isTempNode) {
                this.createTempNode(path);
            } else {
                this.createPersistentNode(path);
            }

        }
    }

    public void createrOrUpdate(String path, String content) {
        try {
            this.getCurrent().setData().forPath(path, content.getBytes("utf-8"));
        } catch (Exception var4) {
            throw new IllegalStateException(var4.getMessage(), var4);
        }
    }

    public void createrOrUpdateInTransaction(String path, String content) {
        try {
            this.getCurrent().inTransaction().setData().forPath(path, content.getBytes("utf-8"));
        } catch (Exception var4) {
            throw new IllegalStateException(var4.getMessage(), var4);
        }
    }

    public void addStateListener(ZookeeperStateListener listener) {
        this.stateListenerPool.add(listener);
    }

    public void removeStateListener(ZookeeperStateListener listener) {
        this.stateListenerPool.remove(listener);
    }

    public Set<ZookeeperStateListener> getSessionListeners() {
        return this.stateListenerPool;
    }

    public void addNodeListener(String path, NodeCacheListener nodeCacheListener) {
        Lock currentLock = this.nodeLock;
        currentLock.lock();

        try {
            if(null == this.nodeCachePool.get(path)) {
                NodeCache e = new NodeCache(this.getCurrent(), path, false);
                e.getListenable().addListener(nodeCacheListener);
                e.start();
                this.nodeCachePool.put(path, e);
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            currentLock.unlock();
        }

    }

    public void removeNodeListener(String path) {
        Lock currentLock = this.nodeLock;
        currentLock.lock();

        try {
            NodeCache e = (NodeCache)this.nodeCachePool.remove(path);
            if(null != e) {
                e.close();
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            currentLock.unlock();
        }

    }

    public void addPathChildrenListener(String path, PathChildrenCacheListener pathChildrenCacheListener) {
        Lock currentLock = this.pathChildrenLock;
        currentLock.lock();

        try {
            if(null == this.pathChildrenCachePool.get(path)) {
                PathChildrenCache e = new PathChildrenCache(this.getCurrent(), path, true);
                e.getListenable().addListener(pathChildrenCacheListener);
                e.start();
                this.pathChildrenCachePool.put(path, e);
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            currentLock.unlock();
        }

    }

    public void removePathChildrenListener(String path) {
        Lock currentLock = this.pathChildrenLock;
        currentLock.lock();

        try {
            PathChildrenCache e = (PathChildrenCache)this.pathChildrenCachePool.remove(path);
            if(null != e) {
                e.close();
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            currentLock.unlock();
        }

    }

    protected void triggerState(State state) {
        Iterator i$ = this.getSessionListeners().iterator();

        while(i$.hasNext()) {
            ZookeeperStateListener sessionListener = (ZookeeperStateListener)i$.next();
            sessionListener.stateChanged(state);
        }

    }

    public void close() {
        if(!this.closed.get()) {
            if(this.closed.compareAndSet(false, true)) {
                try {
                    this.doClose();
                } catch (Throwable var2) {
                    LOGGER.warn(var2.getMessage(), var2);
                }
            }

        }
    }

    public abstract CuratorFramework getCurrent();

    protected abstract void doClose();

    protected abstract void createPersistentNode(String var1);

    protected abstract void createTempNode(String var1);
}
