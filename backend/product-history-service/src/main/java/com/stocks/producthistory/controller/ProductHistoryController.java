package com.stocks.producthistory.controller;

import com.stocks.producthistory.model.Product;
import com.stocks.producthistory.model.ProductHistory;
import com.stocks.producthistory.service.ProductHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/history")
@RequiredArgsConstructor
public class ProductHistoryController {
    private final ProductHistoryService productHistoryService;

    @GetMapping
    public List<ProductHistory> getHistories(){
        return productHistoryService.getProductHistories();
    }

    @GetMapping("/{productId}")
    public List<Product> getHistory(@PathVariable String productId) {
        return  productHistoryService.getProductHistory(productId);
    }
}
