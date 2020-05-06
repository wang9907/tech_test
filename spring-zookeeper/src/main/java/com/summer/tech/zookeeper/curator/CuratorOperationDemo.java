package com.summer.tech.zookeeper.curator;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CreateModable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CuratorOperationDemo {
    public static void main(String[] args) throws InterruptedException {
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
        System.out.println("连接成功。。。");

        //创建节点
        try {
            String result = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/schools/baoan/ersiban", "王诗".getBytes());
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //删除节点
        try {
            curatorFramework.delete().forPath("/schools/baoan/ersiban");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询
        Stat stat = new Stat();
        try {
            byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/schools/baoan/ersiban");
            System.out.println("stat"+stat+new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //更新
        try {
            Stat stat1 = curatorFramework.setData().forPath("/schools/baoan/ersiban", "琉璃岛".getBytes());
            System.out.println(stat1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //异步操作
        ExecutorService service = Executors.newFixedThreadPool(1);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
                @Override
                public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    System.out.println(Thread.currentThread().getName()+"->resultCode"+curatorEvent.getResultCode()+"->"+curatorEvent.getType());
                    countDownLatch.countDown();
                }
            },service).forPath("/schools/jueqi","jueti".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.await();
        service.shutdown();

        //事务操作（curator独有）
        Collection<CuratorTransactionResult> results = null;
        try {
            results = curatorFramework.inTransaction().create().forPath("/transation/test1", "transation".getBytes()).and().
                    setData().forPath("/tansaction/test2", "test2".getBytes()).and().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(CuratorTransactionResult result:results){
            System.out.println(result.getResultStat());
        }

    }
}
