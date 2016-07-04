package zkdemo.zk1;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.utils.ZKPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by dell-pc on 2015/12/11.
 */
public class PathCacheDemo {
    static Logger logger= LoggerFactory.getLogger(PathCacheDemo.class);
    public static void main(String[] args) throws Exception{



            CuratorFramework client= CuratorUtil.createSimple("localhost:2181");
             client.start();

        NodeCache nodeCache=new NodeCache(client,"/test/test9");
        nodeCache.start();

            PathChildrenCache cache=new PathChildrenCache(client,"/test/test9",true);
            cache.start();
            listener(cache);

            TreeCache treeCache=new TreeCache(client,"/test/test9")  ;
        treeCache.start();
        treeListener(treeCache);

            String path=ZKPaths.makePath("/test/test9","/test91");
            String path2=ZKPaths.makePath("/test/test9","/test92");
            String path3=ZKPaths.makePath("/test/test9/test91","/test991");


            client.create().forPath(path,"nimeifffff".getBytes()); //增加
            client.create().forPath(path2,"nimei".getBytes()); //增加
            client.create().forPath(path3,"nimei".getBytes()); //增加

            client.setData().forPath(path,"sfsaf".getBytes());//修改

            logger.info(new String("查询的数据 "+new String(client.getData().forPath(path))));//查询

            client.delete().forPath(path3);                          //删除
            client.delete().forPath(path);                          //删除
            client.delete().forPath(path2);                          //删除
            //System.in.read();
    }



    public static void listener(PathChildrenCache cache){
        PathChildrenCacheListener listener=new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws          Exception {
                switch (pathChildrenCacheEvent.getType()){
                    case  CHILD_ADDED:
                        logger.info("子节点增加 "+pathChildrenCacheEvent.getData()+"  "+pathChildrenCacheEvent.getInitialData());
                        break;
                    case CHILD_REMOVED:
                        logger.info("子节点被删除 "+pathChildrenCacheEvent.getData()+"  "+pathChildrenCacheEvent.getInitialData());
                        break;
                    case CHILD_UPDATED:
                        logger.info("子节点更新 "+pathChildrenCacheEvent.getData()+" "+pathChildrenCacheEvent.getInitialData());
                        break;
                    case CONNECTION_LOST:
                        logger.info("链接被断开 ");
                        break;
                    default:
                        logger.info("其他事件"+pathChildrenCacheEvent.getType()+" ");

                }
            }
        };
        cache.getListenable().addListener(listener);
    }

    public static void treeListener(TreeCache treeCache){
        TreeCacheListener treeCacheListener=new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                 switch (treeCacheEvent.getType()){
                     case  NODE_ADDED:
                         logger.info("tree监控 子节点增加 "+treeCacheEvent.getData()+"  ");
                         break;
                     case NODE_REMOVED:
                         logger.info("tree监控 子节点被删除 "+treeCacheEvent.getData());
                         break;
                     case NODE_UPDATED:
                         logger.info("tree监控 子节点更新 "+treeCacheEvent.getData()+" ");
                         break;
                     case CONNECTION_LOST:
                         logger.info("tree监控 链接被断开 ");
                         break;
                     default:
                         logger.info("tree监控 其他事件"+treeCacheEvent.getType()+" ");

                 }
            }
        } ;
        treeCache.getListenable().addListener(treeCacheListener);
    }

    public  static void nodeCacheListener(final NodeCache nodeCache){
        NodeCacheListener nodeCacheListener=new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                logger.info(new String(nodeCache.getCurrentData().getData()));
            }
        };

    }
}
