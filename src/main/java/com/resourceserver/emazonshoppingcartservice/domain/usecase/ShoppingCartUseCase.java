package com.resourceserver.emazonshoppingcartservice.domain.usecase;

import com.resourceserver.emazonshoppingcartservice.domain.exception.ArticleNotFoundException;
import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.domain.model.ShoppingCart;
import com.resourceserver.emazonshoppingcartservice.domain.model.StockVerificationRequest;
import com.resourceserver.emazonshoppingcartservice.domain.ports.api.ShoppingCartServicePort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.sec.AuthenticatedManagerPort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.spi.ShoppingCartPersistencePort;
import com.resourceserver.emazonshoppingcartservice.domain.validators.StockValidator;
import com.resourceserver.emazonshoppingcartservice.domain.exception.ShoppingCartNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartUseCase implements ShoppingCartServicePort {

    private final ShoppingCartPersistencePort shoppingCartPersistencePort;
    private final AuthenticatedManagerPort authenticatedManagerPort;
    private final StockValidator stockValidator;

    public ShoppingCartUseCase(ShoppingCartPersistencePort shoppingCartPersistencePort,
                               AuthenticatedManagerPort authenticatedManagerPort,
                               StockValidator stockValidator) {

        this.shoppingCartPersistencePort = shoppingCartPersistencePort;
        this.authenticatedManagerPort = authenticatedManagerPort;
        this.stockValidator = stockValidator;
    }

    @Override
    public void addItemToCartShopping(CartItem cartItem) {


        Long userId = authenticatedManagerPort.getUserId();

        StockVerificationRequest stockVerificationRequest = createStockVerificationRequest(userId, cartItem);

        stockValidator.validateStockAvailability(stockVerificationRequest);


        if (shoppingCartPersistencePort.doesCartExist(userId)) {
            shoppingCartPersistencePort.addItemToCart(cartItem, userId);
        } else {
            ShoppingCart shoppingCart = createNewShoppingCart(cartItem, userId);
            shoppingCartPersistencePort.saveShoppingCart(shoppingCart);
        }

    }

    @Override
    public void removeItemFromShoppingCart(Long articleId) {

        Long userId = authenticatedManagerPort.getUserId();

        ShoppingCart shoppingCart = shoppingCartPersistencePort.getShoppingCartByUserId(userId).orElseThrow(
                () -> new ShoppingCartNotFoundException(userId)
        );

        boolean removed = shoppingCart.getItems().removeIf(item -> item.getArticleId().equals(articleId));

        if (!removed) {
            throw new ArticleNotFoundException(articleId);
        }

        shoppingCart.setLastUpdated(LocalDateTime.now());

        shoppingCartPersistencePort.saveShoppingCart(shoppingCart);

    }

    @Override
    public List<CartItem> listCartItems() {

        Long userId = authenticatedManagerPort.getUserId();
        return shoppingCartPersistencePort.getArticleItemsForCart(userId);
    }

    @Override
    public void clearCartItems() {
        Long userId = authenticatedManagerPort.getUserId();

        ShoppingCart shoppingCart = shoppingCartPersistencePort.getShoppingCartByUserId(userId).orElseThrow(
                () -> new ShoppingCartNotFoundException(userId)
        );
        shoppingCart.getItems().clear();
        shoppingCartPersistencePort.saveShoppingCart(shoppingCart);
    }

    @Override
    public void addCartItems(List<CartItem> cartItems) {

        Long userId = authenticatedManagerPort.getUserId();

        ShoppingCart shoppingCart = shoppingCartPersistencePort.getShoppingCartByUserId(userId).orElseThrow(
                () -> new ShoppingCartNotFoundException(userId)
        );
        shoppingCart.getItems().addAll(cartItems);
        shoppingCartPersistencePort.saveShoppingCart(shoppingCart);

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
                shoppingCartPersistencePort.getArticlesIdForCart(userId)
        );

    }
}
