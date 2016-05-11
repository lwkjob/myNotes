package zkdemo;


import org.apache.commons.io.IOUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.ZKPaths;
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

        String hosts="localhost:2181";

        CuratorFramework client = CuratorUtil.createSimple(hosts);
        client.start();

//        byte[] data = client.getData().forPath("/test");
//
//        logger.info("拿到数据" + IOUtils.toString(data, "UTF-8"));
//
//        client.setData().forPath("/test", "nimei".getBytes());
//            String zkpath1= ZKPaths.makePath("/com/xcrm/cfg","/1.0.0");
//        String zkpath2= ZKPaths.makePath("/com/xcrm/cfg/1.0.0","/common");
//        String zkpath3= ZKPaths.makePath("/com/xcrm/cfg/1.0.0/common","/rabbitmq");
//        String zkpath4= ZKPaths.makePath("/com/xcrm/cfg/1.0.0/common/rabbitmq","/address");

        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/com/lcrm/cfg/1.0.0/common/rabbitmq/address");

//        client.create().forPath(zkpath2);
//        client.create().forPath(zkpath3);
//        client.create().forPath(zkpath4,"192.168.18.28".getBytes());
        client.close();

    }


}
