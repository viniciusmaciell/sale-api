package com.letscode.saleapi.repository;

import com.letscode.saleapi.domain.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface CartRepository extends ReactiveMongoRepository<Cart, String> {
    Optional<Mono<Cart>> findByUserId(Long userId);
    Mono<Boolean> existsByUserId(Long userId);

}
