package com.letscode.saleapi.gateway;

import com.letscode.saleapi.domain.ProductCart;
import feign.FeignException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetrySpec;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ProductGateway {

    private final ProductReactiveFeignClient productReactiveFeignClient;

    public Mono<ProductCart> getProduct(String productId) {

        return productReactiveFeignClient.getProduct(productId)
                .onErrorResume(FeignException.NotFound.class, error ->
                        Mono.empty())
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1, 0)));

    }

}
