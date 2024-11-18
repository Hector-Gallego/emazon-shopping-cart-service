package com.resourceserver.emazonshoppingcartservice.ports.driven.feign.adapter;

import com.resourceserver.emazonshoppingcartservice.domain.model.*;
import com.resourceserver.emazonshoppingcartservice.domain.ports.feign.StockFeignServicePort;
import com.resourceserver.emazonshoppingcartservice.ports.driven.feign.interfaces.StockFeignClient;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class StockFeignClientAdapter implements StockFeignServicePort {
    private final StockFeignClient stockFeignClient;

    public StockFeignClientAdapter(StockFeignClient stockFeignClient) {
        this.stockFeignClient = stockFeignClient;
    }

    @Override
    public StockVerificationResponse getAvailableStock(StockVerificationRequest request) {
        return stockFeignClient.getAvailableStock(request);
    }

    @Override
    public PageArticlesCartResponse<ArticleCart> listArticlesCart(PageArticlesCartRequest articlesCartRequest) {
        ResponseEntity<PageArticlesCartResponse<ArticleCart>> response =
                stockFeignClient.listedCartItems(articlesCartRequest);
        return response.getBody();
    }

    @Override
    public SaleData updateStockAndGetSaleData(List<CartItem> cartItems) {
        ResponseEntity<SaleData> response = stockFeignClient.updateStockAndGetSaleData(cartItems);
        return response.getBody();
    }



}
