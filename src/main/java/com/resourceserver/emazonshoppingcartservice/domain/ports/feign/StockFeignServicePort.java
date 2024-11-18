package com.resourceserver.emazonshoppingcartservice.domain.ports.feign;

import com.resourceserver.emazonshoppingcartservice.domain.model.*;

import java.util.List;

public interface StockFeignServicePort {

    StockVerificationResponse getAvailableStock(StockVerificationRequest stockVerificationRequest);
    PageArticlesCartResponse<ArticleCart> listArticlesCart(PageArticlesCartRequest articlesCartRequest);
    SaleData updateStockAndGetSaleData(List<CartItem> cartItems);
}
