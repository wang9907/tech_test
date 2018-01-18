package com.summer.tech.memcached.spy;
import net.spy.memcached.MemcachedClient;
import java.net.InetSocketAddress;

public class MemcachedTest {

    public static void main(String[] args) throws Exception {

        MemcachedClient myClient = new MemcachedClient(new InetSocketAddress("localhost", 11211));
        String myName = "Skyler Tao";
        myClient.set("name", 1, myName);
        System.out.println(myClient.get("name"));

    }
}