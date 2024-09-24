package com.resourceserver.emazonshoppingcartservice.configuration.security.constants;

public final class SecurityConstants {

    private SecurityConstants() {
        throw new IllegalStateException();
    }

    public static final String CLAIM_FIELD_NAME = "role";
    public static final String EMPTY_FIELD = "";
    public static final String CLAIM_FIELD_NAME_USER_ID = "userId";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
}