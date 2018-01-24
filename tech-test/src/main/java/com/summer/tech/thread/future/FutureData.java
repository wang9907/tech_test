package com.summer.tech.thread.future;

/**
 * Created by zhengbin on 2017/11/14
 */
public class FutureData implements Data {
    private RealData realData = null;
    private boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return realData.getResult();
    }
}