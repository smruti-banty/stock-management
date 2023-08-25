package com.stocks.producthistory.service.impl;

import com.stocks.producthistory.model.Product;
import com.stocks.producthistory.model.ProductHistory;
import com.stocks.producthistory.repository.ProductHistoryRepository;
import com.stocks.producthistory.service.ProductHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductHistoryServiceImpl implements ProductHistoryService {
    private final ProductHistoryRepository productHistoryRepository;

    @Async
    @Override
    public void addProductHistory(Product product) {
        var productHistory = ProductHistory.builder().product(product).build();
        productHistoryRepository.insert(productHistory);
        log.info("Product inserted {}", product);
    }

    @Override
    public List<ProductHistory> getProductHistories() {
        return productHistoryRepository.findAll();
    }

    @Override
    public List<Product> getProductHistory(String productId) {
        return productHistoryRepository.findProductsByProductProductId(productId);
    }
}
