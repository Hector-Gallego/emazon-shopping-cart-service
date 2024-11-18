package com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.adapter;

import com.resourceserver.emazonshoppingcartservice.domain.exception.ShoppingCartNotFoundException;
import com.resourceserver.emazonshoppingcartservice.domain.model.CartItem;
import com.resourceserver.emazonshoppingcartservice.domain.model.ShoppingCart;
import com.resourceserver.emazonshoppingcartservice.domain.ports.spi.ShoppingCartPersistencePort;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.entity.CartItemEntity;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.entity.ShoppingCartEntity;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.mapper.CartItemEntityMapper;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.mapper.ShoppingCartEntityMapper;
import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.repository.ShoppingCartRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ShoppingCartJpaAdapter implements ShoppingCartPersistencePort {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartEntityMapper shoppingCartEntityMapper;
    private final CartItemEntityMapper cartItemMapper;

    public ShoppingCartJpaAdapter(ShoppingCartRepository shoppingCartRepository, ShoppingCartEntityMapper shoppingCartEntityMapper, CartItemEntityMapper cartItemMapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartEntityMapper = shoppingCartEntityMapper;

        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public void addItemToCart(CartItem cartItem, Long userId) {

        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new ShoppingCartNotFoundException(userId));

        List<CartItemEntity> itemEntities = shoppingCartEntity.getItems();

        Optional<CartItemEntity> existingItem = itemEntities.stream()
                .filter(item -> item.getArticleId().equals(cartItem.getArticleId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItemEntity itemToUpdate = existingItem.get();
            itemToUpdate.setQuantity(itemToUpdate.getQuantity() + cartItem.getQuantity());
        } else {
            itemEntities.add(cartItemMapper.toEntity(cartItem));
        }

        shoppingCartEntity.setLastUpdated(LocalDateTime.now());
        shoppingCartRepository.save(shoppingCartEntity);


    }

    @Override
    public void saveShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCartEntityMapper.toEntity(shoppingCart));
    }


    @Override
    public boolean doesCartExist(Long userId) {
        return shoppingCartRepository.findByUserId(userId).isPresent();
    }

    @Override
    public List<Long> getArticlesIdForCart(Long userId) {

        ShoppingCartEntity shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow();

        return shoppingCart.getItems().stream()
                .map(CartItemEntity::getArticleId)
                .toList();
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId)
                .map(shoppingCartEntityMapper::toDomain);
    }

    @Override
    public List<CartItem> getArticleItemsForCart(Long userId) {
        ShoppingCartEntity shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow();

        return shoppingCart.getItems().stream().map(cartItemMapper::toDomain).toList();

    }

    @Override
    public Integer getArticleQuantityInCart(Long userId, Long articleId) {
        return shoppingCartRepository.findByUserId(userId)
                .flatMap(cart -> cart.getItems().stream()
                        .filter(item -> item.getArticleId().equals(articleId))
                        .map(CartItemEntity::getQuantity)
                        .findFirst()
                )
                .orElse(0);
    }

}
