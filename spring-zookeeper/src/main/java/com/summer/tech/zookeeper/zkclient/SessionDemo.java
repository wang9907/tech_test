package com.summer.tech.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;

public class SessionDemo {

    private static final String CONNECT_STRING= "localhost:2181";

    public static void main(String[] args){
        ZkClient zkClient = new ZkClient(CONNECT_STRING,5000);
        System.out.println(zkClient+"-->success");
    }
}
