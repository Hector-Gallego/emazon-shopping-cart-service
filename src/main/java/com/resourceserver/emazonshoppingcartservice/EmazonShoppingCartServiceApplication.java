package com.resourceserver.emazonshoppingcartservice;

import com.resourceserver.emazonshoppingcartservice.configuration.security.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@EnableFeignClients
public class EmazonShoppingCartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmazonShoppingCartServiceApplication.class, args);
    }

}
