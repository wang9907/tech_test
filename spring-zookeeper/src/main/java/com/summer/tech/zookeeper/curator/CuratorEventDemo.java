package com.summer.tech.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.*;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CHILD_ADDED;
import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CHILD_REMOVED;
import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CHILD_UPDATED;

public class CuratorEventDemo {
    /**
     * 一种watcher来做节点的监听
     * pathcache  监视一个路径下,子节点的创建、删除、节点数据更新
     * NodeCache  监视一个d当前节点的创建、更新、删除
     * TreeCache  pathcache+nodecache 的合体（监视路径下的创建、更新、删除事件），
     * 缓存路径下的所有子节点的数据
     */
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();

        //节点变化NodeCathe
        NodeCache cache = new NodeCache(curatorFramework,"/curator",false);
        cache.start(true);
        cache.getListenable().addListener(()-> System.out.println("节点发生变化，变化后的结果"+new String(cache.getCurrentData().getData())));
        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/curator","事件测试".getBytes());

        // pathChildrenCache
        PathChildrenCache pathChildrenCache=new PathChildrenCache(curatorFramework,"/event",true);
        cache.start(true);
        // Normal / BUILD_INITIAL_CACHE /POST_INITIALIZED_EVENT
        /*cache.getListenable().addListener((curatorFramework1,pathChildrenCacheEvent)->{
            switch (pathChildrenCacheEvent.getType()){
                case CHILD_ADDED:
                    System.out.println("增加子节点");
                    break;
                case CHILD_REMOVED:
                    System.out.println("删除子节点");
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新子节点");
                    break;
                default:break;
            }
        });*/

        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/event","event".getBytes());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1");
        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/event/event1","1".getBytes());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2");

        curatorFramework.setData().forPath("/event/event1","222".getBytes());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("3");

        curatorFramework.delete().forPath("/event/event1");
        System.out.println("4");

        System.in.read();


    }
}
