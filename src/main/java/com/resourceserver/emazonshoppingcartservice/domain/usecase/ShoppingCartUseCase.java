package com.resourceserver.emazonshoppingcartservice.domain.usecase;

import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.domain.model.ShoppingCart;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;
import com.resourceserver.emazonshoppingcartservice.domain.ports.api.AddItemToCartServicePort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.sec.AuthenticatedManagerPort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.spi.AddItemToCartPersistencePort;
import com.resourceserver.emazonshoppingcartservice.domain.validators.StockValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartUseCase implements AddItemToCartServicePort {

    private final AddItemToCartPersistencePort addItemToCartPersistencePort;
    private final AuthenticatedManagerPort authenticatedManagerPort;
    private final StockValidator stockValidator;

    public ShoppingCartUseCase(AddItemToCartPersistencePort addItemToCartPersistencePort,
                               AuthenticatedManagerPort authenticatedManagerPort,
                               StockValidator stockValidator) {

        this.addItemToCartPersistencePort = addItemToCartPersistencePort;
        this.authenticatedManagerPort = authenticatedManagerPort;
        this.stockValidator = stockValidator;
    }

    @Override
    public void addItemToCartShopping(CartItem cartItem) {


        Long userId = authenticatedManagerPort.getUserId();

        StockVerificationRequest stockVerificationRequest = createStockVerificationRequest(userId, cartItem);

        stockValidator.validateStockAvailability(stockVerificationRequest);


        if (addItemToCartPersistencePort.doesCartExist(userId)) {
            addItemToCartPersistencePort.addItemToCart(cartItem, userId);
        } else {
            ShoppingCart shoppingCart = createNewShoppingCart(cartItem, userId);
            addItemToCartPersistencePort.saveShoppingCart(shoppingCart, userId);
        }

    }


    private ShoppingCart createNewShoppingCart(CartItem cartItem, Long userId) {
        List<CartItem> items = new ArrayList<>();
        items.add(cartItem);
        return new ShoppingCart(userId, items, LocalDateTime.now(), LocalDateTime.now());
    }

    private StockVerificationRequest createStockVerificationRequest(Long userId, CartItem cartItem) {
        return new StockVerificationRequest(
                cartItem.getArticleId(),
                cartItem.getQuantity(),
                addItemToCartPersistencePort.getArticlesIdForCart(userId)
        );

    }
}
