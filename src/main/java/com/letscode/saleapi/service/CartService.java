package com.letscode.saleapi.service;

import com.letscode.saleapi.domain.Cart;
import com.letscode.saleapi.domain.CartRequest;
import com.letscode.saleapi.domain.ProductCart;
import com.letscode.saleapi.gateway.ProductGateway;
import com.letscode.saleapi.gateway.UserGateway;
import com.letscode.saleapi.repository.CartRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductGateway productGateway;
    private final UserGateway userGateway;

    public CartService(CartRepository cartRepository, ProductGateway productGateway, UserGateway userGateway) {
        this.cartRepository = cartRepository;
        this.productGateway = productGateway;
        this.userGateway = userGateway;
    }

    public Mono<Cart> validateCart(CartRequest c) {
        var product = new ProductCart();

        return Mono.zip(
                        userGateway.getUser(c.getUserId()),
                        productGateway.getProduct(c.getProductId()))
                .map(t -> {
                    BeanUtils.copyProperties(t.getT2(), product);
                    return new Cart(t.getT1().getId(), product);
                });
    }

    public Mono<Cart> saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
