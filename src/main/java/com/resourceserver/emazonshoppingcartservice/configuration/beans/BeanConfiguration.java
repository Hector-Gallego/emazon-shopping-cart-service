package com.resourceserver.emazonshoppingcartservice.configuration.beans;

import com.resourceserver.emazonshoppingcartservice.configuration.security.services.AuthenticatedUserManager;
import com.resourceserver.emazonshoppingcartservice.domain.ports.api.ShoppingCartServicePort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.feign.StockFeignServicePort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.sec.AuthenticatedManagerPort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.spi.ShoppingCartPersistencePort;
import com.resourceserver.emazonshoppingcartservice.domain.usecase.ShoppingCartUseCase;
import com.resourceserver.emazonshoppingcartservice.domain.validators.StockValidator;
import com.resourceserver.emazonshoppingcartservice.ports.driven.feign.adapter.StockFeignClientAdapter;
import com.resourceserver.emazonshoppingcartservice.ports.driven.feign.interfaces.StockFeignClient;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.adapter.ShoppingCartJpaAdapter;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.mapper.CartItemEntityMapper;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.mapper.ShoppingCartEntityMapper;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.repository.ShoppingCartRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ShoppingCartServicePort addItemToCartServicePort(
            ShoppingCartPersistencePort shoppingCartPersistencePort,
            AuthenticatedManagerPort authenticatedManagerPort,
            StockValidator stockValidator) {

        return new ShoppingCartUseCase(
                shoppingCartPersistencePort,
                authenticatedManagerPort,
                stockValidator);
    }

    @Bean
    ShoppingCartPersistencePort addItemToCartPersistencePort(
            ShoppingCartRepository shoppingCartRepository,
            ShoppingCartEntityMapper shoppingCartEntityMapper,
            CartItemEntityMapper cartItemEntityMapper) {
        return new ShoppingCartJpaAdapter(shoppingCartRepository, shoppingCartEntityMapper, cartItemEntityMapper);
    }

    @Bean
    StockFeignServicePort stockFeignServicePort(StockFeignClient stockFeignClient) {
        return new StockFeignClientAdapter(stockFeignClient);
    }

    @Bean
    AuthenticatedManagerPort authenticatedManagerPort() {
        return new AuthenticatedUserManager();
    }

    @Bean
    StockValidator stockValidator(StockFeignServicePort stockFeignServicePort) {
        return new StockValidator(stockFeignServicePort);
    }


}
