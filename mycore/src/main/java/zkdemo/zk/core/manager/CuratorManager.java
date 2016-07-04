//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package zkdemo.zk.core.manager;

import com.google.common.base.Charsets;
import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import zkdemo.zk.core.config.ZookeeperConfig;
import zkdemo.zk.core.listener.ZookeeperStateListener;
import zkdemo.zk.core.listener.ZookeeperStateListener.State;
import zkdemo.zk.core.manager.ZookeeperManager;

public class CuratorManager extends AbstractZookeeperManager {
    private final CuratorFramework client;

    public CuratorManager(ZookeeperConfig config) {
        this(config.getClusterAddress(), config.getNamespace(), config.getSessionTimeoutMs(), config.getConnectionTimeoutMs());
    }

    public CuratorManager(String connect, String namespace, int sessionTimeoutMs, int connectionTimeoutMs) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(connect).namespace(namespace).retryPolicy(new RetryNTimes(2147483647, 1000)).sessionTimeoutMs(sessionTimeoutMs).connectionTimeoutMs(connectionTimeoutMs).build();
        curatorFramework.getConnectionStateListenable().addListener(new CuratorManager.DefaultConnectionStateListener());

        try {
            curatorFramework.start();
            curatorFramework.blockUntilConnected();
        } catch (InterruptedException | IllegalStateException var7) {
            ;
        }

        this.client = curatorFramework;
    }

    public void delete(String path, boolean deletingChildrenIfNeeded) {
        try {
            if(deletingChildrenIfNeeded) {
                this.client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
            } else {
                this.client.delete().guaranteed().forPath(path);
            }
        } catch (NoNodeException var4) {
            ;
        } catch (Exception var5) {
            throw new IllegalStateException(var5.getMessage(), var5);
        }

    }

    public List<String> getChildren(String path) {
        try {
            return (List)this.client.getChildren().forPath(path);
        } catch (Exception var3) {
            throw new IllegalStateException(var3.getMessage(), var3);
        }
    }

    public boolean checkExist(String path) {
        try {
            return null != this.client.checkExists().forPath(path);
        } catch (Exception var3) {
            throw new IllegalStateException(var3.getMessage(), var3);
        }
    }

    public String read(String path) throws Exception {
        return new String((byte[])this.client.getData().forPath(path), Charsets.UTF_8);
    }

    public boolean isConnected() {
        return this.client.getZookeeperClient().isConnected();
    }

    public CuratorFramework getCurrent() {
        return this.client;
    }

    protected void doClose() {
        CloseableUtils.closeQuietly(this.client);
    }

    protected void createPersistentNode(String path) {
        try {
            this.client.create().forPath(path);
        } catch (NodeExistsException var3) {
            ;
        } catch (Exception var4) {
            throw new IllegalStateException(var4.getMessage(), var4);
        }

    }

    protected void createTempNode(String path) {
        try {
            ((ACLBackgroundPathAndBytesable)this.client.create().withMode(CreateMode.EPHEMERAL)).forPath(path);
        } catch (NodeExistsException var3) {
            ;
        } catch (Exception var4) {
            throw new IllegalStateException(var4.getMessage(), var4);
        }

    }

    class DefaultConnectionStateListener implements ConnectionStateListener {
        DefaultConnectionStateListener() {
        }

        public void stateChanged(CuratorFramework client, ConnectionState state) {
            switch(state.ordinal()) {
            case 1:
                CuratorManager.this.triggerState(State.DISCONNECTED);
                break;
            case 2:
                CuratorManager.this.triggerState(State.CONNECTED);
                break;
            case 3:
                CuratorManager.this.triggerState(State.RECONNECTED);
            }

        }
    }
}
