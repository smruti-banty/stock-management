package com.stocks.stockhistory.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockHistory {
    @Id
    private String _id;
    private Stock stock;
}
