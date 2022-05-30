package com.letscode.saleapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CartRequest {

    private String userId;
    private String productId;

//    private BigDecimal value;

}
