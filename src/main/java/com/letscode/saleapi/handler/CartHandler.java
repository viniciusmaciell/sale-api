package com.letscode.saleapi.handler;

import com.letscode.saleapi.domain.CartRequest;
import com.letscode.saleapi.service.CartService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class CartHandler {

    private final CartService cartService;

    public CartHandler(CartService cartService) {
        this.cartService = cartService;
    }

    public Mono<ServerResponse> addProductToCart(ServerRequest request) {
        return request.bodyToMono(CartRequest.class)
                .flatMap(cartService::validateCart)
                .flatMap(cartService::saveCart)
                .flatMap(cart -> ServerResponse
                        .created(
                                URI.create(String.format("/cart/%s", cart.getId())))
                        .bodyValue(cart));
    }

    public Mono<ServerResponse> getCart(ServerRequest request) {
        Long userId = Long.valueOf(request.pathVariable("userId"));
        return null;
    }

    public Mono<ServerResponse> deleteProductFromCart(ServerRequest request) {
        return null;
    }
}
