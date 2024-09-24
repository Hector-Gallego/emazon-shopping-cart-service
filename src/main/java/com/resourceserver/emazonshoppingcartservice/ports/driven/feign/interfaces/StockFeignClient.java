package com.resourceserver.emazonshoppingcartservice.ports.driven.feign.interfaces;

import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "emazon-stock-service", url = "http://localhost:8080")
public interface StockFeignClient {

    @PostMapping("/api/stock")
    StockVerificationResponse getAvailableStock(@RequestBody StockVerificationRequest stockVerificationRequest);
}
