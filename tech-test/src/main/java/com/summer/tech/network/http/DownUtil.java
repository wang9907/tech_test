package com.summer.tech.network.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownUtil {

	//下载资源路径
	private String path;
	private String targetFile;
	//线程数量
	private int threadNum;
	// 下载线程对象
	private DownThread[] threads;
	// 下载文件总大小
	private int fileSize;
	//定义一个线程池，在构造函数中初始化成具体类型的线程池
	ExecutorService pool;

	public DownUtil(String path, String targetFile, int threadNum) {
        this.path = path;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
        this.threads = new DownThread[threadNum];
        this.pool = Executors.newFixedThreadPool(threadNum);
    }

	public void download() throws IOException{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(5*1000);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("Connection", "Keep-Alive");
		//得到文件大小
		fileSize = conn.getContentLength();
		conn.disconnect();

		//每个线程要下载的文件部分的大小
		int currentPartSize = fileSize/threadNum+1;
		RandomAccessFile file = new RandomAccessFile(targetFile,"rw");
		file.setLength(fileSize);
		file.close();

		for(int i=0;i<threadNum;i++){
			//计算每个线程的开始位置
			int startPos = 1*currentPartSize;
			//每个线程使用一个RandomAccessFile进行下载
			RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
			//定位该线程下载位置
			currentPart.seek(startPos);
			threads[i]= new DownThread(startPos, currentPartSize, currentPart);
			pool.submit(threads[i]);
		}
		pool.shutdown();
	}

	//获取下载的完成百分比
    public double getCompleteRate() {
        //统计多个线程已经下载的总大小
        int sumSize = 0;
        for (int i = 0; i< threadNum; i++) {
            sumSize += threads[i].length;
        }
        return sumSize * 1.0 / fileSize ;
    }

    private class DownThread implements Runnable{
    	private int startPos;
    	private int currentPartSize;
    	private RandomAccessFile currentPart;
    	public int length;

    	public DownThread(int startPos, int currentPartSize,
                RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }

		public void run() {
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	            conn.setConnectTimeout(5*1000);
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("accept", "*/*");

	            conn.setRequestProperty("Charset", "UTF-8");
                conn.setRequestProperty("Connection", "Keep-Alive");
                InputStream inStream = conn.getInputStream();

              //跳过startPos之前的内容
                inStream.skip(this.startPos);
                byte[] buffer = new byte[1024];
                int hasRead = 0;
             // 读取网络数据，写入本地文件
                while (length < currentPartSize && (hasRead = inStream.read(buffer)) != -1) {
                    currentPart.write(buffer, 0 ,hasRead);
                    length += hasRead;
                }
                currentPart.close();
                inStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

    }
}
