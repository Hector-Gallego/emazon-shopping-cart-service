package com.resourceserver.emazonshoppingcartservice.domain.ports.feign;

import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationResponse;

public interface StockFeignServicePort {

    StockVerificationResponse getAvailableStock(StockVerificationRequest stockVerificationRequest);
}
