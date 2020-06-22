package com.lsk.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class EsLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsLearningApplication.class, args);
    }


    @Bean
    @ConditionalOnProperty("${initial-import.enabled}")
    public SampleDataSet dataSet() {
        return new SampleDataSet();
    }
}
