package com.stocks.stockhistory.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.stockhistory.model.Stock;
import com.stocks.stockhistory.service.StockHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockHistoryListener {
    private static final String STOCK_HISTORY_TOPIC = "cmd.entity.stock";
    private final StockHistoryService stockHistoryService;
    private final ObjectMapper objectMapper;

    @KafkaListener(id = "stockId", topics = STOCK_HISTORY_TOPIC)
    public void addProduct(String data) {
        log.info("Received Product {}", data);
        Stock stock = convertProduct(data);
        stockHistoryService.createStockHistory(stock);
    }

    private Stock convertProduct(String data) {
        Stock stock = null;
        try {
            stock = objectMapper.readValue(data, Stock.class);
        } catch (Exception e) {
            log.error("Conversion Error", e);
        }
        return stock;
    }
}
