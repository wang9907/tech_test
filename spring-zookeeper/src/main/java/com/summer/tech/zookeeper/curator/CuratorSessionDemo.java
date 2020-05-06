package com.summer.tech.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorSessionDemo {
    private static final String CONNECT_STRING = "localhost:2181";

    public static void main(String[] args){
        //创建会话的两种方式
        //normal
//        CuratorFramework curatorFramework= CuratorFrameworkFactory.newClient(CONNECT_STRING,5000,5000,new ExponentialBackoffRetry(1000,3));
//        curatorFramework.start();// start方法启动连接

        //fluent风格
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECT_STRING).retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        curatorFramework.start();
        System.out.println("success");




    }
}
