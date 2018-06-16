package com.elasticsearch.test;

import java.net.UnknownHostException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;

public class GetAPITest {

    public static void main(String[] args) throws UnknownHostException {

        Client client = ESClientUtil.getDefaultESClient();
        GetResponse response = client.prepareGet("twitter", "tweet", "1")
                .execute()
                .actionGet();
        System.out.println(response.toString());
        client.close();

    }

}