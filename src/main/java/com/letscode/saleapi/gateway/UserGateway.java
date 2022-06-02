package com.letscode.saleapi.gateway;

import com.letscode.saleapi.domain.UserCart;
import feign.FeignException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.retry.RetrySpec;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class UserGateway {

    private final UserReactiveFeignClient userReactiveFeignClient;

    public Mono<UserCart> getUser(Long userId) {
        return userReactiveFeignClient.getUser(userId)
                .onErrorResume(FeignException.FeignClientException.NotFound.class, error -> Mono.empty())
                .retryWhen(RetrySpec.fixedDelay(1, Duration.ofSeconds(3, 0)));

    }
}
