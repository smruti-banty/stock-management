package com.stocks.product.repository;

import com.stocks.product.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByIsActive(boolean isActive);
}