package com.resourceserver.emazonshoppingcartservice.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCart {

    private Long id;
    private Long userId;
    private List<CartItem> items;
    private LocalDateTime createdAt;  // Fecha de creación
    private LocalDateTime lastUpdated;

    public ShoppingCart() {
    }

    public ShoppingCart(Long userId, List<CartItem> items, LocalDateTime createdAt, LocalDateTime lastUpdated) {
        this.userId = userId;
        this.items = items;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
