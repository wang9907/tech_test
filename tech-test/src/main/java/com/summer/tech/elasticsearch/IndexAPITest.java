package com.summer.tech.elasticsearch;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import com.fasterxml.jackson.core.JsonProcessingException;

public class IndexAPITest {

    public static void main(String[] args) throws UnknownHostException, JsonProcessingException {
        Client client = ESClientUtil.getDefaultESClient();

        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out Elasticsearch");
        IndexResponse response = client.prepareIndex("twitter", "tweet", "1").setSource(json).execute()
                .actionGet();
        client.close();
    }

}