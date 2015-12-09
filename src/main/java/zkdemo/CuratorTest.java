package zkdemo;


import org.apache.commons.io.IOUtils;
import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tc on 2015/12/9.
 */
public class CuratorTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test1() throws Exception {

        CuratorFramework client = CuratorUtil.createSimple("node1:2181,node2:2181,node3:2181,node4:2181");
        client.start();




        byte[] data = client.getData().forPath("/test");

        logger.info("拿到数据" + IOUtils.toString(data, "UTF-8"));

        client.setData().forPath("/test", "nimei".getBytes());


        client.close();

    }


}
