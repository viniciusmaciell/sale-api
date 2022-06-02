package com.letscode.saleapi.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("cart")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cart {

    @Id
    private String id;
    private Long userId;
    @Field
    private List<ProductCart> products = new ArrayList<>();
    private BigDecimal amount = BigDecimal.ZERO;
    private Date creationDate = new Date();
    private Date updateDate = new Date();

    public Cart(Long userId, ProductCart productCart) {
        this.userId = userId;
        this.products.add(productCart);
        this.amount.add(productCart.getValue());

    }
}
