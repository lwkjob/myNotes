//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package zkdemo.zk.core.manager;

import java.util.List;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import zkdemo.zk.core.listener.ZookeeperStateListener;

public interface ZookeeperManager {
    void create(String var1, boolean var2);

    void delete(String var1, boolean var2);

    boolean checkExist(String var1);

    List<String> getChildren(String var1);

    void addNodeListener(String var1, NodeCacheListener var2);

    void addPathChildrenListener(String var1, PathChildrenCacheListener var2);

    void removePathChildrenListener(String var1);

    void removeNodeListener(String var1);

    void addStateListener(ZookeeperStateListener var1);

    void removeStateListener(ZookeeperStateListener var1);

    boolean isConnected();

    void close();
}
