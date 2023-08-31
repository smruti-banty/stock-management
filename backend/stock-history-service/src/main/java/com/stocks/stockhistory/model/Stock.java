package com.stocks.stockhistory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

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
