package com.resourceserver.emazonshoppingcartservice.ports.driven.feign.adapter;

import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationResponse;
import com.resourceserver.emazonshoppingcartservice.domain.ports.feign.StockFeignServicePort;
import com.resourceserver.emazonshoppingcartservice.ports.driven.feign.interfaces.StockFeignClient;

public class StockFeignClientAdapter implements StockFeignServicePort {
    private final StockFeignClient stockFeignClient;

    public StockFeignClientAdapter(StockFeignClient stockFeignClient) {
        this.stockFeignClient = stockFeignClient;
    }

    @Override
    public StockVerificationResponse getAvailableStock(StockVerificationRequest request) {
        return stockFeignClient.getAvailableStock(request);
    }
}
