package com.stocks.stockhistory.service;

import com.stocks.stockhistory.model.Stock;
import com.stocks.stockhistory.model.StockHistory;

import java.util.List;

public interface StockHistoryService {
    void createStockHistory(Stock stock);

    List<StockHistory> getStockHistories();

    List<Stock> getStockHistory(String stockId);

    List<Stock> getProductStockHistory(String productId);
}
