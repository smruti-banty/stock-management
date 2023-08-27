package com.stocks.stock.controller;

import com.stocks.stock.model.Stock;
import com.stocks.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PutMapping("/{stockId}/{quantity}")
    public void updateStock(@PathVariable String stockId, @PathVariable int quantity) {
        stockService.updateStock(stockId, quantity);
    }

    @PutMapping("/product/{productId}/{quantity}")
    public void updateProductStock(@PathVariable String productId, @PathVariable int quantity) {
        stockService.updateProductStock(productId, quantity);
    }

    @PutMapping("/increase/{stockId}/{quantity}")
    public void increaseStock(@PathVariable String stockId, @PathVariable int quantity) {
        stockService.increaseStock(stockId, quantity);
    }

    @PutMapping("/product/increase/{productId}/{quantity}")
    public void increaseProductStock(@PathVariable String productId, @PathVariable int quantity) {
        stockService.increaseProductStock(productId, quantity);
    }

    @PutMapping("/decrease/{stockId}/{quantity}")
    public void decreaseStock(@PathVariable String stockId, @PathVariable int quantity) {
        stockService.decreaseStock(stockId, quantity);
    }

    @PutMapping("/product/decrease/{productId}/{quantity}")
    public void decreaseProductStock(@PathVariable String productId, @PathVariable int quantity) {
        stockService.decreaseProductStock(productId, quantity);
    }

    @GetMapping("/{stockId}")
    public Stock getStock(@PathVariable String stockId) {
        return stockService.getStock(stockId);
    }

    @GetMapping("/product/{productId}")
    public Stock getProductStock(@PathVariable String productId) {
        return stockService.getProductStock(productId);
    }

    @GetMapping
    public List<Stock> getStocks() {
        return stockService.getStocks();
    }

    @GetMapping("/product/active")
    public List<Stock> getActiveStocks() {
        return stockService.getActiveProductsStocks();
    }

    @GetMapping("/product/inactive")
    public List<Stock> getInActiveStocks() {
        return stockService.getInActiveProductsStocks();
    }
}
