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

        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "sex", "woman","name", "Jame2", "age", "16"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "sex", "woman","name", "Jame2", "age", "16"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "sex", "man", "name", "Jame3", "age", "18"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "sex", "woman","name", "Jame4", "age", "19"));
        request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON, "sex", "man2","name", "Jame5", "age", "26"));
        request.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON, "sex", "man3","name", "Jame6", "age", "99"));
        request.add(new IndexRequest().index("user").id("1007").source(XContentType.JSON, "sex", "man4","name", "Jame7", "age", "26"));
        request.add(new IndexRequest().index("user").id("1008").source(XContentType.JSON, "sex", "man5","name", "Jame8", "age", "66"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems());


        client.close();
    }
}
