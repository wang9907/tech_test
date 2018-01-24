package com.summer.tech.thread.future;

/**
 * Created by zhengbin on 2017/11/14
 */
public class RealData {
    private final String result;

    public RealData(String param) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < 10;i++) {
            sb.append(param);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
        }
        result = sb.toString();
    }

    public String getResult() {
        return result;
    }
}