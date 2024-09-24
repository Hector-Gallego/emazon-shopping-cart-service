package com.resourceserver.emazonshoppingcartservice.domain.validators;

import com.resourceserver.emazonshoppingcartservice.datatest.DataTestFactory;
import com.resourceserver.emazonshoppingcartservice.domain.constants.ErrorMessagesConstants;
import com.resourceserver.emazonshoppingcartservice.domain.exception.CategoryLimitExceededException;
import com.resourceserver.emazonshoppingcartservice.domain.exception.InsufficientStockException;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationResponse;
import com.resourceserver.emazonshoppingcartservice.domain.ports.feign.StockFeignServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StockValidatorTest {

    @Mock
    private StockFeignServicePort stockFeignServicePort;

    @InjectMocks
    private StockValidator stockValidator;


    @Test
     void shouldValidateSufficientStockWhenStockIsAvailable() {

        StockVerificationResponse response = new StockVerificationResponse();
        response.setSufficientStockAvailable(true);
        response.setCategoryLimitExceeded(false);
        response.setNextRestockDate(LocalDate.parse("2024-10-02"));

        StockVerificationRequest request = DataTestFactory.createStockVerificationRequest();

        when(stockFeignServicePort.getAvailableStock(request)).thenReturn(response);

        stockValidator.validateStockAvailability(request);

    }


    @Test
     void shouldThrowInsufficientStockExceptionWhenStockIsUnavailable() {

        StockVerificationResponse response = new StockVerificationResponse();
        response.setSufficientStockAvailable(false);
        response.setNextRestockDate(LocalDate.parse("2024-10-10"));

        StockVerificationRequest request = DataTestFactory.createStockVerificationRequest();

        when(stockFeignServicePort.getAvailableStock(request)).thenReturn(response);

        InsufficientStockException exception = assertThrows(
                InsufficientStockException.class,
                () -> stockValidator.validateStockAvailability(request)
        );


        assertEquals(ErrorMessagesConstants.INSUFFICIENT_STOCK + response.getNextRestockDate(), exception.getMessage());
    }

    @Test
     void shouldThrowCategoryLimitExceededExceptionWhenLimitIsExceeded() {

        StockVerificationResponse response = new StockVerificationResponse();
        response.setSufficientStockAvailable(true);
        response.setCategoryLimitExceeded(true);

        StockVerificationRequest request = DataTestFactory.createStockVerificationRequest();


        when(stockFeignServicePort.getAvailableStock(request)).thenReturn(response);

        CategoryLimitExceededException exception = assertThrows(
                CategoryLimitExceededException.class,
                () -> stockValidator.validateStockAvailability(request)
        );

        assertEquals(ErrorMessagesConstants.CATEGORY_LIMIT_EXCEEDED, exception.getMessage());
    }


}