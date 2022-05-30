package com.letscode.saleapi.config;

import com.letscode.saleapi.handler.CartHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> route(CartHandler cartHandler) {
        return RouterFunctions
                .route(RequestPredicates.POST("v1/cart/add")
                        .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), cartHandler::addProductToCart)
                .andRoute(RequestPredicates.GET("v1/cart/list/{userId}"), cartHandler::getCart)
                .andRoute(RequestPredicates.DELETE("v1/cart/delete/{userId}"), cartHandler::deleteProductFromCart);
    }
}
