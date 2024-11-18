package com.resourceserver.emazonshoppingcartservice.domain.validators;

import com.resourceserver.emazonshoppingcartservice.domain.constants.ErrorMessagesConstants;
import com.resourceserver.emazonshoppingcartservice.domain.exception.CategoryLimitExceededException;
import com.resourceserver.emazonshoppingcartservice.domain.exception.InsufficientStockException;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationResponse;
import com.resourceserver.emazonshoppingcartservice.domain.ports.feign.StockFeignServicePort;

import java.util.List;

public class StockValidator {

    private final StockFeignServicePort stockFeignServicePort;

    public StockValidator(StockFeignServicePort stockFeignServicePort) {
        this.stockFeignServicePort = stockFeignServicePort;
    }

    public void validateStockAvailability(StockVerificationRequest request) {



        StockVerificationResponse response = stockFeignServicePort.getAvailableStock(request);

        validateSufficientStock(response);
        validateCategoryLimit(response);
    }

    private void validateSufficientStock(StockVerificationResponse response) {
        if (Boolean.FALSE.equals(response.getSufficientStockAvailable())) {
            throw new InsufficientStockException(ErrorMessagesConstants.INSUFFICIENT_STOCK + response.getNextRestockDate());
        }
    }

    private void validateCategoryLimit(StockVerificationResponse response) {
        if (Boolean.TRUE.equals(response.getCategoryLimitExceeded())) {
            throw new CategoryLimitExceededException(ErrorMessagesConstants.CATEGORY_LIMIT_EXCEEDED);
        }
    }
}
