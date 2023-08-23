package com.stocks.product.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private String productImage;
    private double productMRP;
    private double purchasePrice;
    private double minimumSellingPrice;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}