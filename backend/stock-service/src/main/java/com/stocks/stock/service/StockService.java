package com.stocks.stock.service;

import com.stocks.stock.model.Product;
import com.stocks.stock.model.Stock;

import java.util.List;

public interface StockService {
    void createStock(Stock stock);

    void updateProduct(Product product);

    void updateStock(String stockId, int quantity);

    void updateProductStock(String productId, int quantity);

    void increaseStock(String stockId, int quantity);

    void increaseProductStock(String productId, int quantity);

    void decreaseStock(String stockId, int quantity);

    void decreaseProductStock(String productId, int quantity);

    Stock getStock(String stockId);

    Stock getProductStock(String productId);

    List<Stock> getStocks();

    List<Stock> getActiveProductsStocks();

    List<Stock> getInActiveProductsStocks();
}
