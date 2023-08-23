package com.stocks.product.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDTO {
    private String productName;
    private String productDescription;
    private String productImage;
    private double productMRP;
    private double purchasePrice;
    private double minimumSellingPrice;
}