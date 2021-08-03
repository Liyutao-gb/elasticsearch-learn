package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author liyutao
 * @since 2021/8/3 0:45
 */
public class ESTest_Client {

    public static void main(String[] args) throws IOException {

        //创建ES客户端
       RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        client.close();



    }

}
