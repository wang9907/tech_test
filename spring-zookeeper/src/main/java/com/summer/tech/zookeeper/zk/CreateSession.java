package com.summer.tech.zookeeper.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CreateSession {
    private static final String CONNECT_STRING = "localhost:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        //创建ZK watcher
        final ZooKeeper zooKeeper = new ZooKeeper(CONNECT_STRING, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                // 如果已经获取连接了SynConnected
                if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();
                    System.out.println(watchedEvent.getState());
                }
                if(watchedEvent.getType()== Event.EventType.NodeDataChanged){
                    // 如果数据发生变化
                    System.out.println("节点发生了变化，路径："+watchedEvent.getPath());
                }
            }
        });
        countDownLatch.await();
//        System.out.println(zooKeeper.getState());

        //创建节点
        // 节点的路径，节点值，ACL
//        zooKeeper.create("/user1","wang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // watcher机制是一次性的，触发一次下次就不会有效了
        //获取数据
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/user1", true, stat);
//        System.out.println(new String(data));
//        System.out.println(stat);

        //修改数据,version=-1表示不做版本控制
        zooKeeper.setData("/user1","wang11".getBytes(),-1);

        zooKeeper.getData("/user1",true,null);
        zooKeeper.setData("/user1","liu11".getBytes(),-1);

        // 删除节点
//        zooKeeper.delete("/user1",-1);
        
        // 获取子节点信息
//        List<String> childrens = zooKeeper.getChildren("/user1", true);
    }
}
