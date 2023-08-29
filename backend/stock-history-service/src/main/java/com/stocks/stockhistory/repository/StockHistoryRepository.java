package com.stocks.stockhistory.repository;

import com.stocks.stockhistory.model.Product;
import com.stocks.stockhistory.model.Stock;
import com.stocks.stockhistory.model.StockHistory;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockHistoryRepository extends MongoRepository<StockHistory, String> {
    @Aggregation(pipeline = {
            "{$match: {'stock.product.productId': ?0}}",
            "{$replaceRoot: {newRoot: '$stock'}}"
    })
    List<Stock> findStockByStockProductProductId(String productId);

    @Aggregation(pipeline = {
            "{$match: {'stock._id': ?0}}",
            "{$replaceRoot: {newRoot: '$stock'}}"
    })
    List<Stock> findStockByStockStockId(String stockId);
}
