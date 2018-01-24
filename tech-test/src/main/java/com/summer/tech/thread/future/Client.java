package com.summer.tech.thread.future;

/**
 * Created by zhengbin on 2017/11/14
 */
public class Client {
    public Data request(final String requestStr) {
        final FutureData futureData = new FutureData();
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(requestStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }
}