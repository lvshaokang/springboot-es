package com.lsk.es.sdk;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

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

    public RestHighLevelClient getClient() {
        return client;
    }

    public void close() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public RestClientBuilder restClientBuilder() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(hostname, port, schema));

        builder.set

    }


}
