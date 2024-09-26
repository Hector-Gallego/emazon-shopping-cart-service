package com.resourceserver.emazonshoppingcartservice.domain.ports.api;

import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;

public interface ShoppingCartServicePort {

    void addItemToCartShopping(CartItem cartItem);
    void removeItemFromShoppingCart(Long articleId);


}
