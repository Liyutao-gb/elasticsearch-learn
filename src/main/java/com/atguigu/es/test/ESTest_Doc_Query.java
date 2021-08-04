package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * @author liyutao
 * @since 2021/8/3 8:03
 */
public class ESTest_Doc_Query {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //1.查询索引的全部数据
        System.out.println("-------------查询索引的全部数据-------------------------------");
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println(response.getHits().getTotalHits());

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        //2.条件查询
        System.out.println("----------条件查询---------------------------------------");
        SearchRequest request2 = new SearchRequest();
        request2.indices("user");
        SearchSourceBuilder sourceBuilder2 = new SearchSourceBuilder().query(QueryBuilders.termQuery("age", "16"));
        request2.source(sourceBuilder2);

        SearchResponse response2 = client.search(request2, RequestOptions.DEFAULT);
        SearchHits hits2 = response2.getHits();
        System.out.println(response2.getHits().getTotalHits());

        for (SearchHit hit : hits2) {
            System.out.println(hit.getSourceAsString());
        }

        //3.分页查询
        System.out.println("---------分页查询---------------------------------------");
        SearchRequest request3 = new SearchRequest();
        request3.indices("user");
        SearchSourceBuilder sourceBuilder3 = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        sourceBuilder3.from(2).size(2);
        request3.source(sourceBuilder3);

        SearchResponse response3 = client.search(request3, RequestOptions.DEFAULT);
        SearchHits hits3 = response3.getHits();
        System.out.println(response3.getHits().getTotalHits());

        for (SearchHit hit : hits3) {
            System.out.println(hit.getSourceAsString());
        }


        //4.排序查询
        /*System.out.println("---------排序查询---------------------------------------");
        SearchRequest request4 = new SearchRequest();
        request4.indices("user");
        SearchSourceBuilder sourceBuilder4 = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        sourceBuilder4.sort("age", SortOrder.DESC);
        request4.source(sourceBuilder4);

        SearchResponse response4 = client.search(request4, RequestOptions.DEFAULT);
        SearchHits hits4 = response4.getHits();
        System.out.println(response4.getHits().getTotalHits());

        for (SearchHit hit : hits4) {
            System.out.println(hit.getSourceAsString());
        }*/

        //5.条件查询
        System.out.println("---------条件查询---------------------------------------");
        SearchRequest request5 = new SearchRequest();
        request5.indices("user");
        SearchSourceBuilder sourceBuilder5 = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        String[] exclude = {"age"};
        String[] include = {};
        sourceBuilder5.fetchSource(include, exclude);
        request5.source(sourceBuilder5);

        SearchResponse response5 = client.search(request5, RequestOptions.DEFAULT);
        SearchHits hits5 = response5.getHits();
        System.out.println(response5.getHits().getTotalHits());

        for (SearchHit hit : hits5) {
            System.out.println(hit.getSourceAsString());
        }

        //6.组合查询
        System.out.println("---------组合查询---------------------------------------");
        SearchRequest request6 = new SearchRequest();
        request6.indices("user");

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//        boolQuery.must(QueryBuilders.matchQuery("age", 18));
//        boolQuery.must(QueryBuilders.matchQuery("sex", "man"));

        boolQuery.should(QueryBuilders.matchQuery("age", 18));
        boolQuery.should(QueryBuilders.matchQuery("sex", "woman"));

        SearchSourceBuilder sourceBuilder6 = new SearchSourceBuilder();
        sourceBuilder6.query(boolQuery);
        request6.source(sourceBuilder6);

        SearchResponse response6 = client.search(request6, RequestOptions.DEFAULT);
        SearchHits hits6 = response6.getHits();
        System.out.println(response6.getHits().getTotalHits());

        for (SearchHit hit : hits6) {
            System.out.println(hit.getSourceAsString());
        }


        //7.范围查询
        System.out.println("---------范围查询---------------------------------------");
        SearchRequest request7 = new SearchRequest();
        request7.indices("user");

        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        rangeQuery.gt("20");
        rangeQuery.lt("30");

        SearchSourceBuilder sourceBuilder7 = new SearchSourceBuilder();
        sourceBuilder7.query(rangeQuery);
        request7.source(sourceBuilder7);

        SearchResponse response7 = client.search(request7, RequestOptions.DEFAULT);
        SearchHits hits7 = response7.getHits();
        System.out.println(response7.getHits().getTotalHits());

        for (SearchHit hit : hits7) {
            System.out.println(hit.getSourceAsString());
        }

        //8.模糊查询
        System.out.println("---------模糊查询---------------------------------------");
        SearchRequest request8 = new SearchRequest();
        request8.indices("user");

        FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("age", "1").fuzziness(Fuzziness.ONE);

        SearchSourceBuilder sourceBuilder8 = new SearchSourceBuilder();
        sourceBuilder8.query(fuzzyQuery);
        request8.source(sourceBuilder8);

        SearchResponse response8 = client.search(request8, RequestOptions.DEFAULT);
        SearchHits hits8 = response8.getHits();
        System.out.println(response8.getHits().getTotalHits());

        for (SearchHit hit : hits8) {
            System.out.println(hit.getSourceAsString());
        }

        //9.高亮查询
        System.out.println("---------高亮查询---------------------------------------");
        SearchRequest request9 = new SearchRequest();
        request9.indices("user");

        SearchSourceBuilder sourceBuilder9 = new SearchSourceBuilder();
        TermQueryBuilder termQuery = QueryBuilders.termQuery("age", "16");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("age");

        sourceBuilder9.highlighter(highlightBuilder);
        sourceBuilder9.query(termQuery);

        request9.source(sourceBuilder9);

        SearchResponse response9 = client.search(request9, RequestOptions.DEFAULT);
        SearchHits hits9 = response9.getHits();
        System.out.println(response9.getHits().getTotalHits());

        for (SearchHit hit : hits9) {
            System.out.println(hit.getSourceAsString());
        }


        //10.聚合查询
        /*System.out.println("---------聚合查询---------------------------------------");
        SearchRequest request11 = new SearchRequest();
        request11.indices("user");

        SearchSourceBuilder sourceBuilder10 = new SearchSourceBuilder();

        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");
        sourceBuilder10.aggregation(aggregationBuilder);

        request11.source(sourceBuilder10);

        SearchResponse response10 = client.search(request11, RequestOptions.DEFAULT);
        SearchHits hits10 = response10.getHits();
        System.out.println(response10.getHits().getTotalHits());

        for (SearchHit hit : hits10) {
            System.out.println(hit.getSourceAsString());
        }*/

        //11.分组查询
        System.out.println("---------分组查询---------------------------------------");
        SearchRequest request11 = new SearchRequest();
        request11.indices("user");

        SearchSourceBuilder sourceBuilder11 = new SearchSourceBuilder();

        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("group").field("age");
        sourceBuilder11.aggregation(aggregationBuilder);

        request11.source(sourceBuilder11);

        SearchResponse response11 = client.search(request11, RequestOptions.DEFAULT);
        SearchHits hits11 = response11.getHits();
        System.out.println(response11.getHits().getTotalHits());

        for (SearchHit hit : hits11) {
            System.out.println(hit.getSourceAsString());
        }


        client.close();
    }
}
