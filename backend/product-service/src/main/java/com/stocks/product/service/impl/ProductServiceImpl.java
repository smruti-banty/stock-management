package com.stocks.product.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.product.model.entity.Product;
import com.stocks.product.repository.ProductRepository;
import com.stocks.product.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    private final KafkaTemplate<String, String> productKafkaTemplate;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic.product.history}")
    private String PRODUCT_HISTORY_TOPIC;

    @Value("${kafka.topic.product.update}")
    private String PRODUCT_UPDATE_TOPIC;

    @Value("${kafka.topic.stock.add}")
    private String PRODUCT_STOCK_TOPIC;

    @Override
    public void addProduct(Product product) {
        log.info("Product received {}", product);

        product.setProductId(getUUID());
        product.setActive(true);
        product.setCreatedDate(currentTime());
        product.setUpdatedDate(currentTime());

        productRepository.insert(product);
        productKafkaTemplate.send(PRODUCT_STOCK_TOPIC, convertString(product));
    }

    @Override
    public void editProduct(String productId, Product newProduct) {
        log.info("Product received {} with id {}", newProduct, productId);

        var oldProduct = getProduct(productId);
        var createdDate = oldProduct.getCreatedDate();
        var isActive = oldProduct.isActive();
        var updatedDate = currentTime();

        newProduct.setProductId(productId);
        newProduct.setCreatedDate(createdDate);
        newProduct.setActive(isActive);
        newProduct.setUpdatedDate(updatedDate);

        productRepository.save(newProduct);
        productKafkaTemplate.send(PRODUCT_HISTORY_TOPIC, convertString(oldProduct));
        productKafkaTemplate.send(PRODUCT_UPDATE_TOPIC, convertString(newProduct));
    }

    @Override
    public void removeProduct(String productId) {
        log.info("Product id received {}", productId);

        var product = getProduct(productId);
        productKafkaTemplate.send(PRODUCT_HISTORY_TOPIC, convertString(product));

        product.setActive(false);
        product.setUpdatedDate(currentTime());
        productRepository.save(product);
        productKafkaTemplate.send(PRODUCT_UPDATE_TOPIC, convertString(product));
    }

    @Override
    public Product getProduct(String productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getActiveProducts() {
        return productRepository.findByIsActive(true);
    }

    @Override
    public List<Product> getInActiveProducts() {
        return productRepository.findByIsActive(false);
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }

    private LocalDateTime currentTime() {
        return LocalDateTime.now();
    }

    private String convertString(Product product) {
        var string = "";
        try {
            string = objectMapper.writeValueAsString(product);
        } catch (Exception e) {
            log.error("Conversion issue", e);
        }
        return string;
    }
}