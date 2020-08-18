package com.lsk.es.example01.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author lsk
 * @class_name ElasticsearchConfig
 * @date 2020-06-28
 */
@Configuration
public class ElasticsearchConfig {

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost("10.36.10.4", 9200));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }


}
