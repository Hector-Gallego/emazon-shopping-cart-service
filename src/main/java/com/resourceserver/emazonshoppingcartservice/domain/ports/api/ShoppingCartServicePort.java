package com.resourceserver.emazonshoppingcartservice.domain.ports.api;

import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;

import java.util.List;

public interface ShoppingCartServicePort {

    void addItemToCartShopping(CartItem cartItem);
    void removeItemFromShoppingCart(Long articleId);
    List<CartItem> listCartItems();
    void clearCartItems();
    void addCartItems(List<CartItem> cartItems);


}
