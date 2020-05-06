package com.summer.tech.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZkclientOperationApiDemo {
    private static final String CONNECT_STRING= "localhost:2181";
    private static ZkClient getInstance(){ return  new ZkClient(CONNECT_STRING,5000);};

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = getInstance();
        // 提供递归创建父节点的功能
//        zkClient.createPersistent("/schools/xinan/ersiban",true);
//        System.out.println("success");

        // 级联删除
        zkClient.deleteRecursive("/schools");

        // 获取子节点
//        List<String> list = zkClient.getChildren("/schools");

        //wathcer机制
        zkClient.subscribeDataChanges("/schools/xinan/ersiban", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("修改节点名称："+s+"修改后的值"+o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("删除节点名称："+s);
            }
        });
        TimeUnit.SECONDS.sleep(2);

        //删除节点
        zkClient.delete("/schools/xinan/ersiban");
        TimeUnit.SECONDS.sleep(2);

        //修改数据
        zkClient.writeData("/shcools/xinan/ersiban","erer");
        TimeUnit.SECONDS.sleep(2);
        zkClient.writeData("/shcools/xinan","xinan");
        TimeUnit.SECONDS.sleep(2);
    }
}
