package com.summer.tech.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ESClientUtil {

    //Elastic Search所在服务器的IP
    public static final String DEFAULT_HOST = "10.10.1.118";

    //Elastic Search与JAVA API交互的默认端口
    public static final int DEFAULT_PORT = 9300;

    public static Client getDefaultESClient() throws UnknownHostException {
        return getESClient(DEFAULT_HOST, DEFAULT_PORT);
    }

    @SuppressWarnings({ "resource", "unchecked" })
    public static Client getESClient(String host, int port) throws UnknownHostException {
        Client client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        return client;
    }

}