//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package zkdemo.zk.env;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.collections.MapUtils;
import zkdemo.zk.exception.ZkException;

public final class ZookeeperEnviroment {
    private static final ZookeeperEnviroment INSTANCE = new ZookeeperEnviroment();
    private final Map<String, Map<String, String>> zkMap = new ConcurrentHashMap();

    public static ZookeeperEnviroment getInstance() {
        return INSTANCE;
    }

    private ZookeeperEnviroment() {
    }

    public boolean containsProperty(String namespace, String key) {
        return this.zkMap.containsKey(namespace)?this.zkMap.containsKey(key):false;
    }

    public void put(String namespace, String key, String value) {
        Map nameSpaceMap = (Map)this.zkMap.get(namespace);
        if(null == nameSpaceMap) {
            nameSpaceMap = new ConcurrentHashMap();
            this.zkMap.put(namespace, nameSpaceMap);
        }

        ((Map)nameSpaceMap).put(key, value);
    }

    public String get(String namespace, String key) {
        Map nameSpaceMap = (Map)this.zkMap.get(namespace);
        return MapUtils.isEmpty(nameSpaceMap)?null:(String)nameSpaceMap.get(key);
    }

    public String getRequired(String namespace, String key) throws IllegalStateException {
        Map nameSpaceMap = (Map)this.zkMap.get(namespace);
        if(MapUtils.isEmpty(nameSpaceMap)) {
            throw new ZkException("Zookeeper namespace:[" + namespace + "] not exists.");
        } else if(!nameSpaceMap.containsKey(key)) {
            throw new ZkException("Zookeeper key:[" + key + "] not exists.");
        } else {
            return (String)nameSpaceMap.get(key);
        }
    }

    public Map<String, String> getDomainMap(String namespace) {
        return (Map)this.zkMap.get(namespace);
    }

    public Map<String, Map<String, String>> getZkMap() {
        return this.zkMap;
    }
}
