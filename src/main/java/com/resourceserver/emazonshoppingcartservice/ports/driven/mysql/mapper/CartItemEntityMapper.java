package com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.mapper;

import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.entity.CartItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemEntityMapper {

    CartItem toDomain(CartItemEntity cartItemEntity);
    CartItemEntity toEntity(CartItem cartItem);
}
