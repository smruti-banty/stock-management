package com.stocks.product.service;

import com.stocks.product.model.entity.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    void editProduct(String productId, Product product);

    void removeProduct(String productId);

    Product getProduct(String productId);

    List<Product> getProducts();

    List<Product> getActiveProducts();

    List<Product> getInActiveProducts();
}

