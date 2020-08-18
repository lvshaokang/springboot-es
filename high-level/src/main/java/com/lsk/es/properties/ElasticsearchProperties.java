package com.lsk.es.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载es配置文件
 *
 * @author red
 * @class_name ElasticsearchProperties
 * @date 2020-06-27
 */
@Data
@Component
@ConfigurationProperties(prefix = "es.config")
public class ElasticsearchProperties {

    private String host;

    private String port;

    private String username;

    private String password;

}
