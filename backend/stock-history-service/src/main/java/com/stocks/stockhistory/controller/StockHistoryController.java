package com.stocks.stockhistory.controller;

import com.stocks.stockhistory.model.Stock;
import com.stocks.stockhistory.model.StockHistory;
import com.stocks.stockhistory.service.StockHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock/history")
@RequiredArgsConstructor
public class StockHistoryController {
    private final StockHistoryService stockHistoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addStockHistory(@RequestBody Stock stock) {
        stockHistoryService.createStockHistory(stock);
    }

    @GetMapping
    public List<StockHistory> getAll() {
        return stockHistoryService.getStockHistories();
    }

    @GetMapping("/stock/{stockId}")
    public List<Stock> getStocksByStockId(@PathVariable String stockId) {
        return stockHistoryService.getStockHistory(stockId);
    }

    @GetMapping("/product/{productId}")
    public List<Stock> getStocksByProductId(@PathVariable String productId) {
        return stockHistoryService.getProductStockHistory(productId);
    }
}
