package com.stocks.product.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ProductTopicConfig {
    @Value("${kafka.topic.product.history}")
    private String PRODUCT_HISTORY_TOPIC;

    @Value("${kafka.topic.product.update}")
    private String PRODUCT_UPDATE_TOPIC;

    @Value("${kafka.topic.stock.add}")
    private String PRODUCT_STOCK_TOPIC;

    @Bean
    public NewTopic productHistoryTopic() {
        return TopicBuilder.name(PRODUCT_HISTORY_TOPIC).build();
    }

    @Bean
    public NewTopic productUpdateTopic() {
        return TopicBuilder.name(PRODUCT_UPDATE_TOPIC).build();
    }

    @Bean
    public NewTopic productStockTopic() {
        return TopicBuilder.name(PRODUCT_STOCK_TOPIC).build();
    }
}
