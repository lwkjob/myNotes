package zkdemo.zk1;


import org.apache.commons.io.IOUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tc on 2015/12/9.
 */
public class CuratorTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 普通客户端建立和简单数据读取和修改
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {

        String hosts="192.168.2.12:2181";

        CuratorFramework client = CuratorUtil.createSimple(hosts);
        client.start();


//
//      client.setData().forPath("/test", "nimei".getBytes());
//      String zkpath1= ZKPaths.makePath("/com/xcrm/cfg","/1.0.0");

//      creatingParentsIfNeeded递归创建父级节点
        String notePath="/com/lcrm/cfg/1.0.0/common/rabbitmq/address";
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(notePath,"lwktest2".getBytes());
//      client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/com2/lcrm/cfg/1.0.0/common/rabbitmq/address","lwktest2".getBytes());
        byte[] data = client.getData().forPath(notePath);
//
        logger.info("拿到数据" + IOUtils.toString(data, "UTF-8"));
        client.close();

    }


}
