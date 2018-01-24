package com.summer.tech.thread.future;

/**
 * Created by zhengbin on 2017/11/14
 */
public class FutureTest {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        Data futureData = client.request("zhengbin");
        System.out.println("request done...");
        Thread.sleep(500);
        System.out.println("other done...");
        System.out.println("result : "+futureData.getResult());
    }
}