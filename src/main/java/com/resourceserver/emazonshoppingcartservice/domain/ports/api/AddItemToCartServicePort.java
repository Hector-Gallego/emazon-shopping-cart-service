package com.resourceserver.emazonshoppingcartservice.domain.ports.api;

import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;

public interface AddItemToCartServicePort {

    void addItemToCartShopping(CartItem cartItem);

}
