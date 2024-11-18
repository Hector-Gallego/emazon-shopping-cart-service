package com.resourceserver.emazonshoppingcartservice.ports.driven.feign.interfaces;

import com.resourceserver.emazonshoppingcartservice.domain.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "emazon-stock-service", url = "http://localhost:8080")
public interface StockFeignClient {

    @PostMapping("/api/stock")
    StockVerificationResponse getAvailableStock(@RequestBody StockVerificationRequest stockVerificationRequest);

    @PostMapping("/api/stock/listCart")
    ResponseEntity<PageArticlesCartResponse<ArticleCart>> listedCartItems(@RequestBody PageArticlesCartRequest requestDto);

    @PostMapping("/api/stock/update")
    ResponseEntity<SaleData> updateStockAndGetSaleData(@RequestBody List<CartItem> cartItems);


}
