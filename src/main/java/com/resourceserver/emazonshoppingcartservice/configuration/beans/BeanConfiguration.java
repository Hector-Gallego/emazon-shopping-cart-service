package com.resourceserver.emazonshoppingcartservice.configuration.beans;

import com.resourceserver.emazonshoppingcartservice.configuration.security.services.AuthenticatedUserManager;
import com.resourceserver.emazonshoppingcartservice.domain.ports.api.AddItemToCartServicePort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.feign.StockFeignServicePort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.sec.AuthenticatedManagerPort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.spi.AddItemToCartPersistencePort;
import com.resourceserver.emazonshoppingcartservice.domain.usecase.AddItemToCartUseCase;
import com.resourceserver.emazonshoppingcartservice.domain.validators.StockValidator;
import com.resourceserver.emazonshoppingcartservice.ports.driven.feign.adapter.StockFeignClientAdapter;
import com.resourceserver.emazonshoppingcartservice.ports.driven.feign.interfaces.StockFeignClient;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.adapter.AddItemToCartJpaAdapter;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.mapper.CartItemEntityMapper;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.mapper.ShoppingCartEntityMapper;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.repository.ShoppingCartRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    AddItemToCartServicePort addItemToCartServicePort(
            AddItemToCartPersistencePort addItemToCartPersistencePort,
            AuthenticatedManagerPort authenticatedManagerPort,
            StockValidator stockValidator) {

        return new AddItemToCartUseCase(
                addItemToCartPersistencePort,
                authenticatedManagerPort,
                stockValidator);
    }

    @Bean
    AddItemToCartPersistencePort addItemToCartPersistencePort(
            ShoppingCartRepository shoppingCartRepository,
            ShoppingCartEntityMapper shoppingCartEntityMapper,
            CartItemEntityMapper cartItemEntityMapper) {
        return new AddItemToCartJpaAdapter(shoppingCartRepository, shoppingCartEntityMapper, cartItemEntityMapper);
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
