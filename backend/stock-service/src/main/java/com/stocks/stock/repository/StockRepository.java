package com.stocks.stock.repository;

import com.stocks.stock.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends MongoRepository<Stock, String> {
    Optional<Stock> findByProductProductId(String productId);

    List<Stock> findByProductIsActive(boolean isActive);
}
