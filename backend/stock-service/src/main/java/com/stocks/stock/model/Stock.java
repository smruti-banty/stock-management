package com.stocks.stock.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    @Id
    private String stockId;
    private Product product;
    private int quantity;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}