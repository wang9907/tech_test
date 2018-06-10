package com.summer.tech.network.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownUtil {

	//������Դ·��
	private String path;
	private String targetFile;
	//�߳�����
	private int threadNum;
	// �����̶߳���
	private DownThread[] threads;
	// �����ļ��ܴ�С
	private int fileSize;
	//����һ���̳߳أ��ڹ��캯���г�ʼ���ɾ������͵��̳߳�
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
		//�õ��ļ���С
		fileSize = conn.getContentLength();
		conn.disconnect();
		
		//ÿ���߳�Ҫ���ص��ļ����ֵĴ�С
		int currentPartSize = fileSize/threadNum+1;
		RandomAccessFile file = new RandomAccessFile(targetFile,"rw");
		file.setLength(fileSize);
		file.close();
		
		for(int i=0;i<threadNum;i++){
			//����ÿ���̵߳Ŀ�ʼλ��
			int startPos = 1*currentPartSize;
			//ÿ���߳�ʹ��һ��RandomAccessFile��������
			RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
			//��λ���߳�����λ��
			currentPart.seek(startPos);
			threads[i]= new DownThread(startPos, currentPartSize, currentPart);
			pool.submit(threads[i]);
		}
		pool.shutdown();
	}
	
	//��ȡ���ص���ɰٷֱ�
    public double getCompleteRate() {
        //ͳ�ƶ���߳��Ѿ����ص��ܴ�С
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
                
              //����startPos֮ǰ������
                inStream.skip(this.startPos);
                byte[] buffer = new byte[1024];
                int hasRead = 0;
             // ��ȡ�������ݣ�д�뱾���ļ�
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
