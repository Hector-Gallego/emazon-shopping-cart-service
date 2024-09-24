package com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.exceptions;

import com.resourceserver.emazonshoppingcartservice.domain.constants.ErrorMessagesConstants;

public class ShoppingCartNotFoundException extends RuntimeException {
    public ShoppingCartNotFoundException(Long userId) {
        super(String.format(ErrorMessagesConstants.SHOPPING_CART_NOT_FOUND, userId));
    }
}