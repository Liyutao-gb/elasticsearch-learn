package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
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
public class ESTest_Index_Delete {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        DeleteIndexRequest request = new DeleteIndexRequest("user");

        //创建索引
        AcknowledgedResponse acknowledged =
                client.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(acknowledged.isAcknowledged());

        client.close();

    }
}
