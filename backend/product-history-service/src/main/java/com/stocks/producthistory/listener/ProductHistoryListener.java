package com.stocks.producthistory.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.producthistory.model.Product;
import com.stocks.producthistory.service.ProductHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductHistoryListener {
    private static final String PRODUCT_HISTORY_TOPIC = "cmd.entity.product";
    private final ProductHistoryService productHistoryService;
    private final ObjectMapper objectMapper;

    @KafkaListener(id = "productId", topics = PRODUCT_HISTORY_TOPIC)
    public void addProduct(String data) {
        log.info("Received Product {}", data);
        Product product = convertProduct(data);
        productHistoryService.addProductHistory(product);
    }

    private Product convertProduct(String data) {
        Product product = null;
        try {
            product = objectMapper.readValue(data, Product.class);
        } catch (Exception e) {
            log.error("Conversion Error", e);
        }
        return product;
    }
}
