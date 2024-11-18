package com.resourceserver.emazonshoppingcartservice.domain.ports.api;

import com.resourceserver.emazonshoppingcartservice.domain.model.*;

import java.util.List;

public interface ShoppingCartServicePort {

    void addItemToCartShopping(CartItem cartItem);
    void removeItemFromShoppingCart(Long articleId);
    List<CartItem> listCartItems();
    PageArticlesCartResponse<ArticleCart> listPageArticlesCart(PageArticlesCartRequest pageArticlesCartRequest);
    void clearCartItems();
    void addCartItems(List<CartItem> cartItems);
    SaleData updateStockGetSaleData();



}
