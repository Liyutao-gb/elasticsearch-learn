package com.atguigu.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author liyutao
 * @since 2021/8/3 8:03
 */
public class ESTest_Doc_Insert_Batch {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //批量插入数据
        BulkRequest request = new BulkRequest();

        IndexRequest request1 = new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "Jame");
        request.add(request1);
        IndexRequest request2 = new IndexRequest().index("user").id("1002").source(XContentType.JSON, "age", "18");
        request.add(request2);
        IndexRequest request3 = new IndexRequest().index("user").id("1003").source(XContentType.JSON, "sex", "man");
        request.add(request3);

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems());


        client.close();
    }
}
