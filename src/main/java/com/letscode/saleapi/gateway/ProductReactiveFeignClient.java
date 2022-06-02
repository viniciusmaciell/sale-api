package com.letscode.saleapi.gateway;

import com.letscode.saleapi.domain.ProductCart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "product-api-instance")
//@ReactiveFeignClient(name = "localhost:8080")
public interface ProductReactiveFeignClient {

    @GetMapping("/v1/products/{productId}")
    Mono<ProductCart> getProduct(@PathVariable("productId") String productId);
}
