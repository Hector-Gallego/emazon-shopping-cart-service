package com.resourceserver.emazonshoppingcartservice.domain.ports.spi;

import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.domain.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartPersistencePort {
    void addItemToCart(CartItem cartItem, Long userId);
    void saveShoppingCart(ShoppingCart shoppingCart);
    boolean doesCartExist(Long userId);
    List<Long> getArticlesIdForCart(Long userId);
    Optional<ShoppingCart> getShoppingCartByUserId(Long userId);
    List<CartItem> getArticleItemsForCart(Long userId);
}
