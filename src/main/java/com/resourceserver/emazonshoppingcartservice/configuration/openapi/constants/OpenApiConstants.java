package com.resourceserver.emazonshoppingcartservice.configuration.openapi.constants;


public final class OpenApiConstants {



    private OpenApiConstants(){
        throw new IllegalStateException();
    }

    public static final String OPENAPI_TITLE = "Emazon shopping cart service API";
    public static final String OPENAPI_VERSION = "1.0";
    public static final String OPENAPI_DESCRIPTION = "API for the shopping cart microservice of the Emazon e-commerce platform";

    public static final String OPENAPI_CODE_400 = "400";
    public static final String OPENAPI_CODE_500 = "500";
    public static final String OPENAPI_CODE_200 = "200";
    public static final String OPENAPI_MEDIA_TYPE_JSON = "application/json";
    public static final String OPENAPI_INTERNAL_SERVER_ERROR = "Internal server error";

    public static final String OPEN_API_ADD_ITEM_SHOPPING_CART_SUMMARY = "Add an item to the shopping cart";
    public static final String OPEN_API_ADD_ITEM_SHOPPING_CART_DESCRIPTION = "This endpoint allows users to add an item to their shopping cart, updating the quantity if the item already exists.";

    public static final String CART_ITEM_ADDED = "The item has been successfully added to the shopping cart";

    public static final String INVALID_INPUT = "Invalid input";


}
