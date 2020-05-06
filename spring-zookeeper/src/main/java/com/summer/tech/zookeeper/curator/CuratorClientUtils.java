package com.summer.tech.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorClientUtils {
    private static CuratorFramework curatorFramework;
    private final static String CONNECT_STRING="localhost:2181";

    public static CuratorFramework getInstance(){
        curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_STRING,5000,5000,new ExponentialBackoffRetry(1000,3));
        return curatorFramework;
    }
}
