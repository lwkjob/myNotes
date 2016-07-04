//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package zkdemo.zk;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;
import zkdemo.zk.core.config.ZookeeperConfig;
import zkdemo.zk.core.manager.CuratorManager;
import zkdemo.zk.core.manager.CuratorManagerFramework;
import zkdemo.zk.env.ZookeeperEnviroment;

public class ZookeeperConfigService {
    protected final String clusterAddress;
    private static final String DEFAULTZKROOTPATH = "/com/lwk/cfg/1.0.0";
    private final String zkRootPath;
    private final CuratorManager curatorManager;
    static final int SESSIONT_IMEOUT_MS = 3000;
    static final int CONNECTIONT_IMEOUT_MS = 3000;

    public ZookeeperConfigService(String clusterAddress, String zkRootPath) {
        this.clusterAddress = clusterAddress;
        this.zkRootPath = StringUtils.hasText(zkRootPath)?zkRootPath:"/com/lwk/cfg/1.0.0";
        this.curatorManager = CuratorManagerFramework.build(ZookeeperConfig.builder().cluster(clusterAddress).connectionTimeoutMs(3000).sessionTimeoutMs(3000).build());
    }

    public final void iterator(String domainSpace, String configPath) {
        try {
            String e = this.zkRootPath + configPath;
            String value = this.curatorManager.read(e);
            if(StringUtils.hasText(value)) {
                ZookeeperEnviroment.getInstance().put(domainSpace, configPath, value);
            }

            List childreKeys = this.curatorManager.getChildren(this.zkRootPath + configPath);
            if(!CollectionUtils.isEmpty(childreKeys)) {
                Iterator i$ = childreKeys.iterator();

                while(i$.hasNext()) {
                    String childKey = (String)i$.next();
                    String currentpath = configPath + "/" + childKey;
                    this.iterator(domainSpace, currentpath);
                }
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }
}
