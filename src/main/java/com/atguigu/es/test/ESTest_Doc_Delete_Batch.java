package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author liyutao
 * @since 2021/8/3 8:03
 */
public class ESTest_Doc_Delete_Batch {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //批量插入数据
        BulkRequest request = new BulkRequest();

        DeleteRequest request1 = new DeleteRequest().index("user").id("1001");
        request.add(request1);
        DeleteRequest request2 = new DeleteRequest().index("user").id("1002");
        request.add(request2);
        DeleteRequest request3 = new DeleteRequest().index("user").id("1003");
        request.add(request3);

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems());


        client.close();
    }
}
