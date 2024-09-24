package com.resourceserver.emazonshoppingcartservice.configuration.security.constants;

public final class ApiEndPointsConstants {

    private ApiEndPointsConstants(){
        throw new IllegalStateException();
    }
    public static final String API_CATEGORY_URI = "/api/category";
    public static final String API_BRAND_URI = "/api/brand";
    public static final String API_ARTICLE_URI = "/api/article";
    public static final String API_STOCK_URI = "/api/article/stock";
}