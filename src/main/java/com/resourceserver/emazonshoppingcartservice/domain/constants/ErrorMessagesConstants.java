package com.resourceserver.emazonshoppingcartservice.domain.constants;

public class ErrorMessagesConstants {

    private ErrorMessagesConstants(){
        throw new IllegalStateException();
    }

    public static final String INVALID_FIELDS = "Uno o más campos son inválidos";
    public static final String INSUFFICIENT_STOCK = "Stock insuficiente, fecha estimada de llegada: ";
    public static final String CATEGORY_LIMIT_EXCEEDED = "No se permite más de 3 artículos por categoría.";
    public static final String SHOPPING_CART_NOT_FOUND = "Carrito de compras no encontrado para el usuario: %d";
    public static final String ARTICLE_ID_NOT_NULL = "El ID del artículo no puede ser nulo";
    public static final String ARTICLE_ID_POSITIVE = "El ID del artículo debe ser positivo";
    public static final String QUANTITY_NOT_NULL = "La cantidad no puede ser nula";
    public static final String QUANTITY_MIN_VALUE = "La cantidad debe ser al menos 1";
    public static final String ARTICLE_NOT_FOUND = "Artículo con id: %d no encontrado";

}
