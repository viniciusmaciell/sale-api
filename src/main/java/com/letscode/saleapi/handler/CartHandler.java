package com.letscode.saleapi.handler;

import com.letscode.saleapi.domain.Cart;
import com.letscode.saleapi.domain.CartRequest;
import com.letscode.saleapi.domain.UserCart;
import com.letscode.saleapi.service.CartService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartHandler {

    private final CartService cartService;

/*    public Mono<ServerResponse> addProductToCart(ServerRequest request) {
        return request.bodyToMono(Cart.class)
                .flatMap(cartService::validateCart)
                .flatMap(cartService::saveCart)
                .flatMap(cart -> ServerResponse
                        .created(
                                URI.create(String.format("/cart/%s", cart.getId())))
                        .bodyValue(cart));
//            .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue(""));
    }*/

    public Mono<ServerResponse> addProductToCart(ServerRequest request) {
        return request.bodyToMono(CartRequest.class)
                .flatMap(cartService::validateCartRequest)
                .flatMap(cartService::saveCart)
                .flatMap(cart -> ServerResponse
                        .created(
                                URI.create(String.format("/cart/%s", cart.getId())))
                        .bodyValue(cart));
    }

    public Mono<ServerResponse> getCart(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> deleteProductFromCart(ServerRequest request) {
        return null;
    }

}
