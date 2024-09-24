package com.resourceserver.emazonshoppingcartservice.domain.exception;

public class CategoryLimitExceededException extends RuntimeException{
    public CategoryLimitExceededException(String message){
        super(message);
    }
}
