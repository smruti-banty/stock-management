package com.stocks.stock.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.stock.model.Product;
import com.stocks.stock.model.Stock;
import com.stocks.stock.repository.StockRepository;
import com.stocks.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final KafkaTemplate<String, String> stockKafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic.stock.history}")
    private String STOCK_HISTORY_TOPIC;

    @Override
    public void createStock(Product product) {
        log.info("Data received {}", product);

        var currentTime = currentTime();
        var stockId = getUUID();
        var updatedBy = "Admin";

        var stock = Stock.builder()
                .stockId(stockId)
                .product(product)
                .createdDate(currentTime)
                .updatedDate(currentTime)
                .updatedBy(updatedBy)
                .build();

        stockRepository.insert(stock);
    }

    @Override
    public void updateProduct(Product product) {
        var productId = product.getProductId();
        var stock = getProductStock(productId);

        stock.setProduct(product);
        stockRepository.save(stock);
    }


    @Override
    public void updateStock(String stockId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();
        var stock = getStock(stockId);

        stockKafkaTemplate.send(STOCK_HISTORY_TOPIC, convertString(stock));

        stock.setQuantity(quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public void updateProductStock(String productId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();
        var stock = getProductStock(productId);
        stockKafkaTemplate.send(STOCK_HISTORY_TOPIC, convertString(stock));

        stock.setQuantity(quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public void increaseStock(String stockId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();
        var stock = getStock(stockId);
        var currentQuantity = stock.getQuantity();

        stockKafkaTemplate.send(STOCK_HISTORY_TOPIC, convertString(stock));

        stock.setQuantity(currentQuantity + quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public void increaseProductStock(String productId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();
        var stock = getProductStock(productId);
        var currentQuantity = stock.getQuantity();

        stockKafkaTemplate.send(STOCK_HISTORY_TOPIC, convertString(stock));

        stock.setQuantity(currentQuantity + quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public void decreaseStock(String stockId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();
        var stock = getStock(stockId);
        var currentQuantity = stock.getQuantity();

        stockKafkaTemplate.send(STOCK_HISTORY_TOPIC, convertString(stock));

        stock.setQuantity(currentQuantity - quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public void decreaseProductStock(String productId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();
        var stock = getProductStock(productId);
        var currentQuantity = stock.getQuantity();

        stockKafkaTemplate.send(STOCK_HISTORY_TOPIC, convertString(stock));

        stock.setQuantity(currentQuantity - quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public Stock getStock(String stockId) {
        return stockRepository.findById(stockId).orElseThrow();
    }

    @Override
    public Stock getProductStock(String productId) {
        return stockRepository.findByProductProductId(productId).orElseThrow();
    }

    @Override
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    @Override
    public List<Stock> getActiveProductsStocks() {
        return stockRepository.findByProductIsActive(true);
    }

    @Override
    public List<Stock> getInActiveProductsStocks() {
        return stockRepository.findByProductIsActive(false);
    }

    private String convertString(Stock stock) {
        var string = "";
        try {
            string = objectMapper.writeValueAsString(stock);
        } catch (Exception e) {
            log.error("Conversion issue", e);
        }
        return string;
    }

    private LocalDateTime currentTime() {
        return LocalDateTime.now();
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }
}
