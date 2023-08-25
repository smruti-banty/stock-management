package com.stocks.producthistory.repository;

import com.stocks.producthistory.model.Product;
import com.stocks.producthistory.model.ProductHistory;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductHistoryRepository extends MongoRepository<ProductHistory, String> {

    @Aggregation(pipeline = {
            "{$match: {'product._id': ?0}}",
            "{$replaceRoot: {newRoot: '$product'}}"
    })
    List<Product> findProductsByProductProductId(String productId);
}
