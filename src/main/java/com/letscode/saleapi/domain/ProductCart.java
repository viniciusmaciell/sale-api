package com.letscode.saleapi.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCart {
    private String id;
    private String name;
    private BigDecimal value;
}
