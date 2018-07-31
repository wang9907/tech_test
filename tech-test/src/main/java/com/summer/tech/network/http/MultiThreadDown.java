package com.summer.tech.network.http;

import java.io.IOException;

public class MultiThreadDown {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 初始化DownUtil
        final DownUtil downUtil = new DownUtil(
                "http://sw.bos.baidu.com/sw-search-sp/software/7d662d80a3d85/npp_7.2_Installer.exe",
                "notepad.exe",4);
        //开始下载
        downUtil.download();
        Thread monitor =  new Thread(new Runnable(){

            @Override
            public void run() {
                while(downUtil.getCompleteRate() <= 1) {
                    //每隔1秒查看一次完成进度
                    System.out.println("已完成："+downUtil.getCompleteRate());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        });
        monitor.start();
        monitor.join();
        System.out.println("下载完成");
    }
}