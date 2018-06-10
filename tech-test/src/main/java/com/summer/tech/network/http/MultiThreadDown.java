package com.summer.tech.network.http;

import java.io.IOException;

public class MultiThreadDown {
    public static void main(String[] args) throws IOException, InterruptedException {
        // ��ʼ��DownUtil
        final DownUtil downUtil = new DownUtil(
                "http://sw.bos.baidu.com/sw-search-sp/software/7d662d80a3d85/npp_7.2_Installer.exe",
                "notepad.exe",4);
        //��ʼ����
        downUtil.download();
        Thread monitor =  new Thread(new Runnable(){

            @Override
            public void run() {
                while(downUtil.getCompleteRate() <= 1) {
                    //ÿ��1��鿴һ����ɽ���
                    System.out.println("����ɣ�"+downUtil.getCompleteRate());
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
        System.out.println("�������");
    }
}