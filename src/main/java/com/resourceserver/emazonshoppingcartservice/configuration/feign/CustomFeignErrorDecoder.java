package com.resourceserver.emazonshoppingcartservice.configuration.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resourceserver.emazonshoppingcartservice.configuration.exception.exceptionhandle.CustomErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;


import java.io.IOException;
import java.io.InputStream;

public class CustomFeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public CustomFeignErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public Exception decode(String s, Response response) throws RuntimeException {

        CustomErrorResponse customErrorResponse;
        try (InputStream bodyIs = response.body().asInputStream()) {

            customErrorResponse = objectMapper.readValue(bodyIs, CustomErrorResponse.class);
            return new CustomFeignException(customErrorResponse.getStatus(), customErrorResponse.getMessage());

        } catch (IOException e) {

            return new RuntimeException(e.getMessage());
        }

    }
}
