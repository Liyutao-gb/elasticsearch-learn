package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * @author liyutao
 * @since 2021/8/3 8:03
 */
public class ESTest_Index_Search {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        GetIndexRequest request = new GetIndexRequest("user");

        //创建索引
        GetIndexResponse response =
                client.indices().get(request, RequestOptions.DEFAULT);

        System.out.println(response.getAliases());

        System.out.println(response.getMappings());

        System.out.println(response.getSettings());

        client.close();
    }
}
