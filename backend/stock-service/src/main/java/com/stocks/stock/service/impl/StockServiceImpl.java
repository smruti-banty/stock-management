package com.stocks.stock.service.impl;

import com.stocks.stock.model.Product;
import com.stocks.stock.model.Stock;
import com.stocks.stock.repository.StockRepository;
import com.stocks.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    @Override
    public void createStock(Stock stock) {
        log.info("Data received {}", stock);
        var currentTime = currentTime();
        var stockId = getUUID();
        var updatedBy = "Admin";

        stock.setStockId(stockId);
        stock.setCreatedDate(currentTime);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.insert(stock);
    }

    @Override
    public void updateProduct(Product product) {
        var productId = product.getProductId();
        var stock = getProductStock(productId);

        stock.setProduct(product);
        stockRepository.save(stock);
    }


    @Override
    public void updateStock(String stockId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();
        var stock = getStock(stockId);

        stock.setQuantity(quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public void updateProductStock(String productId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();
        var stock = getProductStock(productId);

        stock.setQuantity(quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }
    @Override
    public void increaseStock(String stockId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();

        var stock = getStock(stockId);
        var currentQuantity = stock.getQuantity();

        stock.setQuantity(currentQuantity + quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public void increaseProductStock(String productId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();

        var stock = getProductStock(productId);
        var currentQuantity = stock.getQuantity();

        stock.setQuantity(currentQuantity + quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public void decreaseStock(String stockId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();

        var stock = getStock(stockId);
        var currentQuantity = stock.getQuantity();

        stock.setQuantity(currentQuantity - quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public void decreaseProductStock(String productId, int quantity) {
        var updatedBy = "Admin";
        var currentTime = LocalDateTime.now();

        var stock = getProductStock(productId);
        var currentQuantity = stock.getQuantity();

        stock.setQuantity(currentQuantity - quantity);
        stock.setUpdatedDate(currentTime);
        stock.setUpdatedBy(updatedBy);

        stockRepository.save(stock);
    }

    @Override
    public Stock getStock(String stockId) {
        return stockRepository.findById(stockId).orElseThrow();
    }

    @Override
    public Stock getProductStock(String productId) {
        return stockRepository.findByProductProductId(productId).orElseThrow();
    }

    @Override
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    @Override
    public List<Stock> getActiveProductsStocks() {
        return stockRepository.findByProductIsActive(true);
    }

    @Override
    public List<Stock> getInActiveProductsStocks() {
        return stockRepository.findByProductIsActive(false);
    }

    private LocalDateTime currentTime() {
        return LocalDateTime.now();
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }
}
