package com.stocks.producthistory.service;

import com.stocks.producthistory.model.Product;
import com.stocks.producthistory.model.ProductHistory;

import java.util.List;

public interface ProductHistoryService {
    void addProductHistory(Product product);

    List<ProductHistory> getProductHistories();

    List<Product> getProductHistory(String productId);
}
