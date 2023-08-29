package com.stocks.stockhistory.service.impl;

import com.stocks.stockhistory.model.Stock;
import com.stocks.stockhistory.model.StockHistory;
import com.stocks.stockhistory.repository.StockHistoryRepository;
import com.stocks.stockhistory.service.StockHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockHistoryServiceImpl implements StockHistoryService {
    private final StockHistoryRepository stockHistoryRepository;

    @Override
    public void createStockHistory(Stock stock) {
        var stockHistory = StockHistory.builder().stock(stock).build();
        stockHistoryRepository.insert(stockHistory);
    }

    @Override
    public List<StockHistory> getStockHistories() {
        return stockHistoryRepository.findAll();
    }

    @Override
    public List<Stock> getStockHistory(String stockId) {
        return stockHistoryRepository.findStockByStockStockId(stockId);
    }

    @Override
    public List<Stock> getProductStockHistory(String productId) {
        return stockHistoryRepository.findStockByStockProductProductId(productId);
    }
}
