package com.letscode.saleapi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@ToString
public class CartRequest {

    private Long userId;
    private String productId;

}
