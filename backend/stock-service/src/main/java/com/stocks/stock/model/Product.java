package com.stocks.stock.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private String productId;
    private String productName;
    private String productDescription;
    private String productImage;
    private double productMRP;
    private double purchasePrice;
    private double minimumSellingPrice;
    private boolean isActive;
}