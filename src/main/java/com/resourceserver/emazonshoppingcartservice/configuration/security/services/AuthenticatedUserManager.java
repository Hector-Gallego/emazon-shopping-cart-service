package com.resourceserver.emazonshoppingcartservice.configuration.security.services;


import com.resourceserver.emazonshoppingcartservice.configuration.security.constants.SecurityConstants;
import com.resourceserver.emazonshoppingcartservice.domain.ports.sec.AuthenticatedManagerPort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;

public class AuthenticatedUserManager implements AuthenticatedManagerPort {

    @Override
    public Long getUserId() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> claims = authentication.getToken().getClaims();
        return (Long) claims.get(SecurityConstants.CLAIM_FIELD_NAME_USER_ID);
    }
}