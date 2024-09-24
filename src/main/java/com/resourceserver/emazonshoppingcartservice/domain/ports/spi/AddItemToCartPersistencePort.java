package com.resourceserver.emazonshoppingcartservice.domain.ports.spi;

import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.domain.model.ShoppingCart;

import java.util.List;

public interface AddItemToCartPersistencePort {
    void addItemToCart(CartItem cartItem, Long userId);
    void saveShoppingCart(ShoppingCart shoppingCart, Long userId);
    boolean doesCartExist(Long userId);
    List<Long> getArticlesIdForCart(Long userId);
}
