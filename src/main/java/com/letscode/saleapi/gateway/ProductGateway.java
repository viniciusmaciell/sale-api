package com.letscode.saleapi.gateway;

import com.letscode.saleapi.domain.ProductCart;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.RetrySpec;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ProductGateway {
    private final WebClient webClient;

    @Value("${products.base.url}")
    private String baseUrl;

    public ProductGateway() {
        this.webClient = WebClient.builder().build();
    }

    public Mono<ProductCart> getProduct(String productId) {
        return WebClient
                .builder()
                .baseUrl(String.format(baseUrl, productId))
                .build()
                .get()
                .retrieve()
                .bodyToMono(ProductCart.class)

                .onErrorResume(WebClientResponseException.class, e ->
                        e.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(e)) //todo excecao
                .retryWhen(RetrySpec.fixedDelay(3, Duration.ofSeconds(1, 0)));
    }

}
