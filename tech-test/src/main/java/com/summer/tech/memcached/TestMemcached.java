/*package com.summer.tech.memcached;

import java.io.IOException;
import java.sql.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class TestMemcached {
	public static void main(String[] args) throws IOException {
		MemCachedClient client=new MemCachedClient();  
        String [] addr ={"127.0.0.1:11211"};  
        Integer [] weights = {3};  
        SockIOPool pool = SockIOPool.getInstance();  
        pool.setServers(addr);  
        pool.setWeights(weights);  
        pool.setInitConn(5);  
        pool.setMinConn(5);  
        pool.setMaxConn(200);  
        pool.setMaxIdle(1000*30*30);  
        pool.setMaintSleep(30);  
        pool.setNagle(false);  
        pool.setSocketTO(30);  
        pool.setSocketConnectTO(0);  
        pool.initialize();  
          
//      String [] s  =pool.getServers();  
        client.setCompressEnable(true);  
        client.setCompressThreshold(1000*1024);  
          
//      �����ݷ��뻺��  
        client.set("test2","test2");  
          
//      �����ݷ��뻺��,������ʧЧʱ��  
        Date date=new Date(2000000);  
        client.set("test1","test1", date);  
          
//      ɾ����������  
//      client.delete("test1");  
          
//      ��ȡ��������  
        String str =(String)client.get("test1");  
        System.out.println(str);  
		
	}
}*/