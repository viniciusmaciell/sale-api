package com.letscode.saleapi.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Document("cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {

    @Id
    private String id;
    private Long userId;
    @Field
    private List<ProductCart> products;
    private BigDecimal amount = BigDecimal.ZERO;
    private Date creationDate = new Date();
    private Date updateDate = new Date();
}
