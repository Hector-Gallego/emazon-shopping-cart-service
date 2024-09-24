package com.resourceserver.emazonshoppingcartservice.domain.usecase;

import com.resourceserver.emazonshoppingcartservice.domain.constants.ErrorMessagesConstants;
import com.resourceserver.emazonshoppingcartservice.domain.exception.CategoryLimitExceededException;
import com.resourceserver.emazonshoppingcartservice.domain.exception.InsufficientStockException;
import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.domain.model.ShoppingCart;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;
import com.resourceserver.emazonshoppingcartservice.domain.ports.sec.AuthenticatedManagerPort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.spi.AddItemToCartPersistencePort;
import com.resourceserver.emazonshoppingcartservice.domain.validators.StockValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddItemToCartUseCaseTest {

    @Mock
    private AuthenticatedManagerPort authenticatedManagerPort;

    @Mock
    private StockValidator stockValidator;

    @Mock
    private AddItemToCartPersistencePort addItemToCartPersistencePort;

    @InjectMocks
    private AddItemToCartUseCase addItemToCartUseCase;

    @Test
    void shouldAddItemToCartWhenCartExists() {
        Long userId = 1L;
        CartItem cartItem = new CartItem(1L, 10L, 10);

        when(authenticatedManagerPort.getUserId()).thenReturn(userId);
        when(addItemToCartPersistencePort.doesCartExist(userId)).thenReturn(true);

        addItemToCartUseCase.addItemToCartShopping(cartItem);

        verify(stockValidator).validateStockAvailability(any(StockVerificationRequest.class));
        verify(addItemToCartPersistencePort).addItemToCart(cartItem, userId);
        verify(addItemToCartPersistencePort, never()).saveShoppingCart(any(), eq(userId));
    }

    @Test
    void shouldSaveShoppingCartWhenCartDoesNotExist() {
        Long userId = 1L;
        CartItem cartItem = new CartItem(1L, 10L, 10);

        when(authenticatedManagerPort.getUserId()).thenReturn(userId);
        when(addItemToCartPersistencePort.doesCartExist(userId)).thenReturn(false);

        addItemToCartUseCase.addItemToCartShopping(cartItem);

        verify(stockValidator).validateStockAvailability(any(StockVerificationRequest.class));
        verify(addItemToCartPersistencePort, never()).addItemToCart(any(), eq(userId));
        verify(addItemToCartPersistencePort).saveShoppingCart(any(ShoppingCart.class), eq(userId));
    }

    @Test
    void shouldThrowInsufficientStockException_WhenStockIsInsufficient() {
        Long userId = 1L;
        CartItem cartItem = new CartItem(1L, 10L, 10);

        when(authenticatedManagerPort.getUserId()).thenReturn(userId);

        doThrow(new InsufficientStockException(ErrorMessagesConstants.INSUFFICIENT_STOCK))
                .when(stockValidator).validateStockAvailability(any(StockVerificationRequest.class));

        InsufficientStockException exception = assertThrows(
                InsufficientStockException.class,
                () -> addItemToCartUseCase.addItemToCartShopping(cartItem)
        );
        assertEquals(ErrorMessagesConstants.INSUFFICIENT_STOCK, exception.getMessage());

        verify(addItemToCartPersistencePort, never()).addItemToCart(any(), eq(userId));
        verify(addItemToCartPersistencePort, never()).saveShoppingCart(any(), eq(userId));
    }

    @Test
    void shouldThrowCategoryLimitExceededException_WhenCategoryLimitIsExceeded() {
        Long userId = 1L;
        CartItem cartItem = new CartItem(1L, 10L, 10);


        when(authenticatedManagerPort.getUserId()).thenReturn(userId);


        doThrow(new CategoryLimitExceededException(ErrorMessagesConstants.CATEGORY_LIMIT_EXCEEDED))
                .when(stockValidator).validateStockAvailability(any(StockVerificationRequest.class));

        CategoryLimitExceededException exception = assertThrows(
                CategoryLimitExceededException.class,
                () -> addItemToCartUseCase.addItemToCartShopping(cartItem)
        );
        assertEquals(ErrorMessagesConstants.CATEGORY_LIMIT_EXCEEDED, exception.getMessage());

        verify(addItemToCartPersistencePort, never()).addItemToCart(any(), eq(userId));
        verify(addItemToCartPersistencePort, never()).saveShoppingCart(any(), eq(userId));
    }
}