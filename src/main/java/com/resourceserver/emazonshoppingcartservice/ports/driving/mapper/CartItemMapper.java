package com.resourceserver.emazonshoppingcartservice.ports.driving.mapper;


import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.ports.driving.dto.request.CartItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItem toDomain(CartItemDto cartItemDto);
    CartItemDto toDto(CartItem cartItem);
}
