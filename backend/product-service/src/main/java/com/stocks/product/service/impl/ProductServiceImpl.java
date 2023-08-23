package com.stocks.product.service.impl;

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
    private final KafkaTemplate<String, Product> productKafkaTemplate;
    private final ProductRepository productRepository;
    @Value("${kafka.topic.product.history}")
    private final String productHistoryTopic;

    @Override
    public void addProduct(Product product) {
        log.info("Product received {}", product);

        product.setProductId(getUUID());
        product.setActive(true);
        product.setCreatedDate(currentTime());
        product.setUpdatedDate(currentTime());

        productRepository.insert(product);
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
        productKafkaTemplate.send(productHistoryTopic, oldProduct);
    }

    @Override
    public void removeProduct(String productId) {
        log.info("Product id received {}", productId);

        var product = getProduct(productId);
        productKafkaTemplate.send(productHistoryTopic, product);

        product.setActive(false);
        product.setUpdatedDate(currentTime());
        productRepository.save(product);
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
}