package com.summer.tech.zookeeper.zk;

import org.apache.curator.utils.ZookeeperFactory;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.io.PipedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AuthControllerDemo implements Watcher {
    private static final String CONNECT_STRING="localhost:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static CountDownLatch countDownLatch1 = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper(CONNECT_STRING,5000,new AuthControllerDemo());
        //等待连接完成
        countDownLatch.await();

        ACL acl= new ACL(ZooDefs.Perms.ALL,new Id("digest", DigestAuthenticationProvider.generateDigest("root:root")));
        ACL acl1= new ACL(ZooDefs.Perms.CREATE,new Id("ip","192.168.0.1"));

        List<ACL> acls = new ArrayList<>();
        acls.add(acl);
        acls.add(acl1);

        //创建节点，acls自己定义权限
        zooKeeper.create("/auth1","123".getBytes(),acls, CreateMode.PERSISTENT);
        // 添加授权信息，就是账户信息
        zooKeeper.addAuthInfo("digest","root:root".getBytes());

        zooKeeper.create("/auth1/auth1-1","124".getBytes(),ZooDefs.Ids.CREATOR_ALL_ACL,CreateMode.PERSISTENT);

        ZooKeeper zooKeeper1 = new ZooKeeper(CONNECT_STRING,5000,new AuthControllerDemo());
        countDownLatch.await();
        zooKeeper1.addAuthInfo("digest","root:root".getBytes());
        zooKeeper1.delete("/auth1/auth1-1",-1);

        // 级联新增

        //级联删除

    }




    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
