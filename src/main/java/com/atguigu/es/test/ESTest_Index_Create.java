package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @author liyutao
 * @since 2021/8/3 7:58
 */
public class ESTest_Index_Create {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        CreateIndexRequest request = new CreateIndexRequest("user");

        //创建索引
        CreateIndexResponse response =
                client.indices().create(request, RequestOptions.DEFAULT);

        boolean acknowledged = response.isAcknowledged();

        System.out.println(acknowledged);

        client.close();

    }
}
