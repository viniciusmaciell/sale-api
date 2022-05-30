package com.letscode.saleapi.service;

import com.letscode.saleapi.domain.Cart;
import com.letscode.saleapi.domain.CartRequest;
import com.letscode.saleapi.domain.ProductCart;
import com.letscode.saleapi.domain.UserCart;
import com.letscode.saleapi.gateway.ProductGateway;
import com.letscode.saleapi.gateway.UserGateway;
import com.letscode.saleapi.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final ProductGateway productGateway;
    private final UserGateway userGateway;

/*        public Mono<Cart> validateCart(Cart c) {
            return Mono.zip(
                    Mono.just(c).flatMap(cart -> userGateway.getUser(cart.getUserId())),
                    Mono.just(c).flatMap(cart -> productGateway.getProduct(cart.getProducts()
                            .stream()
                            .map(ProductCart::getId)
                            .collect(Collectors.joining())))

            ).map(tuple -> c);
        }*/


    public Mono<Cart> validateCartRequest(CartRequest c) {
        var user = userGateway.getUser(c.getUserId());
        var product = productGateway.getProduct(c.getProductId());
        final Mono<Cart>  cartMono= user.flatMap(userCart ->saveCart() );


        return null;

    }

    ;

//        log.info(userCart.toString());
//        log.info(productCart.toString());
//        var cart = new Cart();
//        return Mono.zip(
//                Mono.just(c).flatMap(cartRequest -> userGateway.getUser(cartRequest.getUserId())),
//                Mono.just(c).flatMap(cartRequest -> productGateway.getProduct(cartRequest.getProductId()))
//
//        ).map(t -> new Cart());

    public Mono<Cart> fromCartRequestToCartEntity(Mono<CartRequest> cartRequestMono) {
        return null;
    }


    public Mono<Cart> fromAddProductRequestToCart(Mono<?> c) {
        return null;
    }

    public Mono<Cart> saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
