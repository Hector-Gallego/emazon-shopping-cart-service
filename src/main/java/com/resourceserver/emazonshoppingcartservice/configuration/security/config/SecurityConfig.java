package com.resourceserver.emazonshoppingcartservice.configuration.security.config;


import com.resourceserver.emazonshoppingcartservice.configuration.exception.delegatehandle.DelegateAccessDeniedHandler;
import com.resourceserver.emazonshoppingcartservice.configuration.exception.delegatehandle.DelegateAuthenticationEntryPoint;
import com.resourceserver.emazonshoppingcartservice.configuration.security.constants.RoleNameConstants;
import com.resourceserver.emazonshoppingcartservice.configuration.security.constants.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RsaKeyProperties rsaKeys;
    private final DelegateAuthenticationEntryPoint authenticationEntryPoint;
    private final DelegateAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(RsaKeyProperties rsaKeys, DelegateAuthenticationEntryPoint authenticationEntryPoint, DelegateAccessDeniedHandler accessDeniedHandler) {
        this.rsaKeys = rsaKeys;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/cart").hasAuthority(RoleNameConstants.CLIENT)
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> {
                            jwt.jwtAuthenticationConverter(jwtAuthenticationConverter());
                            jwt.decoder(jwtDecoder());
                        })
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){

        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(SecurityConstants.CLAIM_FIELD_NAME);
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(SecurityConstants.EMPTY_FIELD);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;

    }
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }


}
