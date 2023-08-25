package com.stocks.product.config;

import org.springframework.kafka.support.serializer.JsonSerializer;
import com.stocks.product.model.entity.Product;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ProductTopicConfig {
    @Value("${kafka.topic.product.history}")
    private String productHistoryTopic;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(productHistoryTopic).build();
    }
}
