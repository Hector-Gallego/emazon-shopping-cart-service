package com.resourceserver.emazonshoppingcartservice.datatest;

import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;

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
}
