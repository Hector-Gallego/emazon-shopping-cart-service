package com.resourceserver.emazonshoppingcartservice.domain.constants;

public class ErrorMessagesConstants {

    private ErrorMessagesConstants(){
        throw new IllegalStateException();
    }

    public static final String INVALID_FIELDS = "One or more fields are invalid";
    public static final String INSUFFICIENT_STOCK = "Not enough stock, restock available on: ";
    public static final String CATEGORY_LIMIT_EXCEEDED = "No more than 3 items per category allowed.";
    public static final String SHOPPING_CART_NOT_FOUND = "Shopping cart not found for user: %d";
    public static final String ARTICLE_ID_NOT_NULL = "Article ID cannot be null";
    public static final String ARTICLE_ID_POSITIVE = "Article ID must be positive";
    public static final String QUANTITY_NOT_NULL = "Quantity cannot be null";
    public static final String QUANTITY_MIN_VALUE = "Quantity must be at least 1";
}
