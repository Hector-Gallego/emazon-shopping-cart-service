package com.resourceserver.emazonshoppingcartservice.configuration.feign;

import com.resourceserver.emazonshoppingcartservice.configuration.security.constants.SecurityConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenPropagator implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        String jwtToken = authentication.getToken().getTokenValue();

        requestTemplate.header(SecurityConstants.AUTHORIZATION_HEADER,
                SecurityConstants.BEARER_PREFIX + jwtToken);


    }
}