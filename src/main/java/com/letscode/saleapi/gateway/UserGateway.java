package com.letscode.saleapi.gateway;

import com.letscode.saleapi.domain.UserCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
public class UserGateway {

    @Value("${user.base.url}")
    private String baseUrl;

    public Mono<UserCart> getUser(String userId) {
        return WebClient
                .builder()
                .baseUrl(String.format(baseUrl, userId))
                .build()
                .get()
                .retrieve()
                .bodyToMono(UserCart.class)

                .onErrorResume(WebClientResponseException.class, e ->
                        e.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(e)) //todo adicionar exception
                .retryWhen(RetrySpec.fixedDelay(1, Duration.ofSeconds(3, 0)));

    }
}
