package com.letscode.saleapi.gateway;

import com.letscode.saleapi.domain.UserCart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "user-api-instance")
//@ReactiveFeignClient(name = "localhost:8081")
public interface UserReactiveFeignClient {

    @GetMapping("/v1/users/{userId}")
    Mono<UserCart> getUser(@PathVariable("userId") Long userId);
}
