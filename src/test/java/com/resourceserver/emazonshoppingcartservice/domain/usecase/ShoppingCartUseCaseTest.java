package com.resourceserver.emazonshoppingcartservice.domain.usecase;

import com.resourceserver.emazonshoppingcartservice.datatest.DataTestFactory;
import com.resourceserver.emazonshoppingcartservice.domain.constants.ErrorMessagesConstants;
import com.resourceserver.emazonshoppingcartservice.domain.exception.ArticleNotFoundException;
import com.resourceserver.emazonshoppingcartservice.domain.exception.CategoryLimitExceededException;
import com.resourceserver.emazonshoppingcartservice.domain.exception.InsufficientStockException;
import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.domain.model.ShoppingCart;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;
import com.resourceserver.emazonshoppingcartservice.domain.ports.sec.AuthenticatedManagerPort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.spi.ShoppingCartPersistencePort;
import com.resourceserver.emazonshoppingcartservice.domain.validators.StockValidator;
import com.resourceserver.emazonshoppingcartservice.domain.exception.ShoppingCartNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartUseCaseTest {

    @Mock
    private AuthenticatedManagerPort authenticatedManagerPort;

    @Mock
    private StockValidator stockValidator;

    @Mock
    private ShoppingCartPersistencePort shoppingCartPersistencePort;

    @InjectMocks
    private ShoppingCartUseCase shoppingCartUseCase;

    @Test
    void shouldAddItemToCartWhenCartExists() {
        Long userId = 1L;
        CartItem cartItem = new CartItem(1L, 10L, 10);

        when(authenticatedManagerPort.getUserId()).thenReturn(userId);
        when(shoppingCartPersistencePort.doesCartExist(userId)).thenReturn(true);

        shoppingCartUseCase.addItemToCartShopping(cartItem);

        verify(stockValidator).validateStockAvailability(any(StockVerificationRequest.class));
        verify(shoppingCartPersistencePort).addItemToCart(cartItem, userId);
        verify(shoppingCartPersistencePort, never()).saveShoppingCart(any());
    }

    @Test
    void shouldSaveShoppingCartWhenCartDoesNotExist() {
        Long userId = 1L;
        CartItem cartItem = DataTestFactory.createCartItem();

        when(authenticatedManagerPort.getUserId()).thenReturn(userId);
        when(shoppingCartPersistencePort.doesCartExist(userId)).thenReturn(false);

        shoppingCartUseCase.addItemToCartShopping(cartItem);

        verify(stockValidator).validateStockAvailability(any(StockVerificationRequest.class));
        verify(shoppingCartPersistencePort, never()).addItemToCart(any(), eq(userId));
        verify(shoppingCartPersistencePort).saveShoppingCart(any(ShoppingCart.class));
    }

    @Test
    void shouldThrowInsufficientStockException_WhenStockIsInsufficient() {
        Long userId = 1L;
        CartItem cartItem = DataTestFactory.createCartItem();

        when(authenticatedManagerPort.getUserId()).thenReturn(userId);

        doThrow(new InsufficientStockException(ErrorMessagesConstants.INSUFFICIENT_STOCK))
                .when(stockValidator).validateStockAvailability(any(StockVerificationRequest.class));

        InsufficientStockException exception = assertThrows(
                InsufficientStockException.class,
                () -> shoppingCartUseCase.addItemToCartShopping(cartItem)
        );
        assertEquals(ErrorMessagesConstants.INSUFFICIENT_STOCK, exception.getMessage());

        verify(shoppingCartPersistencePort, never()).addItemToCart(any(), eq(userId));
        verify(shoppingCartPersistencePort, never()).saveShoppingCart(any());
    }

    @Test
    void shouldThrowCategoryLimitExceededExceptionWhenCategoryLimitIsExceeded() {
        Long userId = 1L;
        CartItem cartItem = DataTestFactory.createCartItem();

        when(authenticatedManagerPort.getUserId()).thenReturn(userId);

        doThrow(new CategoryLimitExceededException(ErrorMessagesConstants.CATEGORY_LIMIT_EXCEEDED))
                .when(stockValidator).validateStockAvailability(any(StockVerificationRequest.class));

        CategoryLimitExceededException exception = assertThrows(
                CategoryLimitExceededException.class,
                () -> shoppingCartUseCase.addItemToCartShopping(cartItem)
        );
        assertEquals(ErrorMessagesConstants.CATEGORY_LIMIT_EXCEEDED, exception.getMessage());

        verify(shoppingCartPersistencePort, never()).addItemToCart(any(), eq(userId));
        verify(shoppingCartPersistencePort, never()).saveShoppingCart(any());
    }


    @Test
    void shouldRemoveItemFromShoppingCart() {

        Long userId = 1L;
        Long articleId = 1L;
        ShoppingCart shoppingCart = DataTestFactory.createValidShoppingCart(userId);

        when(authenticatedManagerPort.getUserId()).thenReturn(userId);
        when(shoppingCartPersistencePort.getShoppingCartByUserId(userId)).thenReturn(Optional.of(shoppingCart));

        shoppingCartUseCase.removeItemFromShoppingCart(articleId);

        assertEquals(1, shoppingCart.getItems().size());

        assertNotNull(shoppingCart.getLastUpdated());
        verify(shoppingCartPersistencePort, times(1)).saveShoppingCart(shoppingCart);
    }

    @Test
    void shouldThrowShoppingCartNotFoundExceptionWhenShoppingCartNotFound() {

        Long userId = 1L;
        Long articleId = 1L;


        when(authenticatedManagerPort.getUserId()).thenReturn(userId);
        when(shoppingCartPersistencePort.getShoppingCartByUserId(userId)).thenReturn(Optional.empty());


        ShoppingCartNotFoundException exception = assertThrows(ShoppingCartNotFoundException.class,
                () -> shoppingCartUseCase.removeItemFromShoppingCart(articleId));

        assertEquals(String.format(ErrorMessagesConstants.SHOPPING_CART_NOT_FOUND, userId),
                exception.getMessage());

        verify(shoppingCartPersistencePort, never()).saveShoppingCart(any());
    }

    @Test
    void shouldUpdateLastModifiedDateWhenItemRemoved() {

        Long userId = 1L;
        Long articleId = 1L;
        ShoppingCart shoppingCart = DataTestFactory.createValidShoppingCart(userId);

        when(authenticatedManagerPort.getUserId()).thenReturn(userId);
        when(shoppingCartPersistencePort.getShoppingCartByUserId(userId)).thenReturn(Optional.of(shoppingCart));

        LocalDateTime lastUpdatedBefore = shoppingCart.getLastUpdated();

        shoppingCartUseCase.removeItemFromShoppingCart(articleId);

        assertTrue(shoppingCart.getLastUpdated().isAfter(lastUpdatedBefore));

        verify(shoppingCartPersistencePort, times(1)).saveShoppingCart(shoppingCart);
    }

    @Test
    void shouldThrowArticleNotFoundExceptionWhenArticleNotFound(){
        Long userId = 1L;
        Long articleId = 4L;

        ShoppingCart shoppingCart = DataTestFactory.createValidShoppingCart(userId);

        when(authenticatedManagerPort.getUserId()).thenReturn(userId);
        when(shoppingCartPersistencePort.getShoppingCartByUserId(userId)).thenReturn(Optional.of(shoppingCart));


        ArticleNotFoundException exception = assertThrows(ArticleNotFoundException.class,
                () -> shoppingCartUseCase.removeItemFromShoppingCart(articleId));

        assertEquals(String.format(ErrorMessagesConstants.ARTICLE_NOT_FOUND, articleId),
                exception.getMessage());

        verify(shoppingCartPersistencePort, never()).saveShoppingCart(any());
    }
}