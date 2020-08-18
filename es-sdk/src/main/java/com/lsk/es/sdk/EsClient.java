package com.lsk.es.sdk;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * TODO
 *
 * @author Red
 * @class_name EsClient
 * @date 2020/08/18
 */
@Slf4j
public class EsClient {

    protected RestHighLevelClient client;

    private String hostname;
    private int port;
    private String schema;

    public RestClientBuilder restClientBuilder() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(hostname, port, schema));

        builder.set

    }


}
