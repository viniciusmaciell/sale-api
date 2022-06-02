package com.letscode.saleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
public class SaleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleApiApplication.class, args);
    }

}
