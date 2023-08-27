package com.stocks.stock.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.stock.model.Product;
import com.stocks.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockListener {
    private static final String PRODUCT_UPDATE_TOPIC = "cmd.update.product";
    private static final String PRODUCT_STOCK_TOPIC = "cmd.product.stock";

    private final ObjectMapper objectMapper;
    private final StockService stockService;

    @KafkaListener(id = "updateProductFromStock", topics = PRODUCT_UPDATE_TOPIC)
    public void updateProduct(String data) {
        log.info("Received Product {}", data);

        Product product = convertProduct(data);
        stockService.updateProduct(product);
    }

    @KafkaListener(id = "updateProductFromStock", topics = PRODUCT_STOCK_TOPIC)
    public void createStock(String data) {
        log.info("Received Product {}", data);

        Product product = convertProduct(data);
        stockService.createStock(product);
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
