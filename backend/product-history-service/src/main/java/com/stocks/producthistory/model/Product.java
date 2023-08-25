package com.stocks.producthistory.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Field("_id")
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