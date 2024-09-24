package com.resourceserver.emazonshoppingcartservice.configuration.exception.constants;

public class ExceptionsConstants {

    private ExceptionsConstants(){
        throw new IllegalStateException();



    }
    public static final String UNEXPECTED_ERROR_MESSAGE = "an unexpected error occurred";
    public static final String LOG_EXCEPTION_ERROR_MESSAGE = "An error occurred: {}, Exception Type: {},";
}
