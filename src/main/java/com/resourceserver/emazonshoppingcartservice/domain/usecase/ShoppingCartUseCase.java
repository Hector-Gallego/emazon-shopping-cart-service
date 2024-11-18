package com.resourceserver.emazonshoppingcartservice.domain.usecase;

import com.resourceserver.emazonshoppingcartservice.domain.exception.ArticleNotFoundException;
import com.resourceserver.emazonshoppingcartservice.domain.model.*;
import com.resourceserver.emazonshoppingcartservice.domain.ports.api.ShoppingCartServicePort;
import com.resourceserver.emazonshoppingcartservice.domain.ports.feign.StockFeignServicePort;
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
    private final StockFeignServicePort stockFeignServicePort;

    public ShoppingCartUseCase(ShoppingCartPersistencePort shoppingCartPersistencePort,
                               AuthenticatedManagerPort authenticatedManagerPort,
                               StockValidator stockValidator, StockFeignServicePort stockFeignServicePort) {

        this.shoppingCartPersistencePort = shoppingCartPersistencePort;
        this.authenticatedManagerPort = authenticatedManagerPort;
        this.stockValidator = stockValidator;
        this.stockFeignServicePort = stockFeignServicePort;
    }

    @Override
    public void addItemToCartShopping(CartItem cartItem) {


        Long userId = authenticatedManagerPort.getUserId();
        if (!shoppingCartPersistencePort.doesCartExist(userId)) {
            ShoppingCart shoppingCart = createNewShoppingCart(userId);
            shoppingCartPersistencePort.saveShoppingCart(shoppingCart);
        }

        StockVerificationRequest stockVerificationRequest = createStockVerificationRequest(userId, cartItem);
        stockValidator.validateStockAvailability(stockVerificationRequest);
        shoppingCartPersistencePort.addItemToCart(cartItem, userId);


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
    public PageArticlesCartResponse<ArticleCart> listPageArticlesCart(PageArticlesCartRequest pageArticlesCartRequest) {

        List<CartItem> cartItems = listCartItems();
        pageArticlesCartRequest.setArticlesCart(cartItems);
        return stockFeignServicePort.listArticlesCart(pageArticlesCartRequest);
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

    @Override
    public SaleData updateStockGetSaleData() {
        Long userId = authenticatedManagerPort.getUserId();
        List<CartItem> cartItems = shoppingCartPersistencePort.getArticleItemsForCart(userId);
        clearCartItems();
        return stockFeignServicePort.updateStockAndGetSaleData(cartItems);
    }


    private ShoppingCart createNewShoppingCart(Long userId) {
        List<CartItem> items = new ArrayList<>();
        return new ShoppingCart(userId, items, LocalDateTime.now(), LocalDateTime.now());
    }

    private StockVerificationRequest createStockVerificationRequest(Long userId, CartItem cartItem) {

        Integer currentQuantityInCart = shoppingCartPersistencePort
                .getArticleQuantityInCart(userId, cartItem.getArticleId());

        Integer totalQuantity = currentQuantityInCart + cartItem.getQuantity();
        return new StockVerificationRequest(
                cartItem.getArticleId(),
                totalQuantity,
                shoppingCartPersistencePort.getArticlesIdForCart(userId)
        );

    }
}
