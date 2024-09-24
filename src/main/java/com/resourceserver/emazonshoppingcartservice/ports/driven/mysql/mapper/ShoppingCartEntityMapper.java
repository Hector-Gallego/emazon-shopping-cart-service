package com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.mapper;

import com.resourceserver.emazonshoppingcartservice.domain.model.ShoppingCart;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.entity.ShoppingCartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartEntityMapper {


    ShoppingCartEntity toEntity(ShoppingCart shoppingCart);
    ShoppingCart toDomain(ShoppingCartEntity shoppingCartEntity);
}
