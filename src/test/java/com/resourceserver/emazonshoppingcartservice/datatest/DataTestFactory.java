package com.resourceserver.emazonshoppingcartservice.datatest;

import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.domain.model.ShoppingCart;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataTestFactory {

    public static CartItem createCartItem() {
        return new  CartItem(1L, 10L, 10);
    }

    public static StockVerificationRequest createStockVerificationRequest(){
        StockVerificationRequest request = new StockVerificationRequest();
        request.setCartArticlesIds(List.of(1L, 2L, 3L));
        request.setQuantity(10);
        request.setArticleId(1L);
        return request;
    }

    public static ShoppingCart createValidShoppingCart(Long userId){
        CartItem item1 = new CartItem(1L, 1L, 10 );
        CartItem item2 = new CartItem(2L,  2L, 10);

        List<CartItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        return new ShoppingCart(userId,
                items,
                LocalDateTime.now(),
                LocalDateTime.now().minusHours(10));
    }


}
