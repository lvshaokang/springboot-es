package com.lsk.es.example02.service;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.ConstantScoreQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.query.QuerySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author lsk
 * @class_name TermQueryService01
 * @date 2020-07-27
 */
public class TermQueryService01 {

    @Autowired
    private RestHighLevelClient client;

    public void bulkLoad() throws IOException {
        IndexRequest indexRequest01 = new IndexRequest()
                .id("1")
                .source(loadDoc01());
        IndexRequest indexRequest02 = new IndexRequest()
                .id("2")
                .source(loadDoc02());
        IndexRequest indexRequest03 = new IndexRequest()
                .id("3")
                .source(loadDoc03());

        BulkRequest bulkRequest = new BulkRequest("products")
                .add(indexRequest01)
                .add(indexRequest02)
                .add(indexRequest03);

        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    public void termQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsQueryBuilder termQueryBuilder = QueryBuilders
                .termsQuery("desc", "iphone")
                .boost(1.0f);
        searchSourceBuilder.query(termQueryBuilder);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("products");
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    }

    public void termQuery1() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsQueryBuilder termQueryBuilder = QueryBuilders
                .termsQuery("desc", "iphone");
//                .boost(1.0f);
        ConstantScoreQueryBuilder constantScoreQueryBuilder = QueryBuilders
                .constantScoreQuery(termQueryBuilder);

        searchSourceBuilder.query(constantScoreQueryBuilder);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("products");
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    }

    public Map<String,String> loadDoc01() {
        Map<String, String> map = new HashMap<>();
        map.put("productID","XHDK-A-1293-#fJ3");
        map.put("desc","iPhone");
        return map;
    }

    public Map<String,String> loadDoc02() {
        Map<String, String> map = new HashMap<>();
        map.put("productID","KHDE-B-9947-#kL5");
        map.put("desc","iPad");
        return map;
    }

    public Map<String,String> loadDoc03() {
        Map<String, String> map = new HashMap<>();
        map.put("productID","JODL-X-1937-#pV7");
        map.put("desc","MBP");
        return map;
    }
}
