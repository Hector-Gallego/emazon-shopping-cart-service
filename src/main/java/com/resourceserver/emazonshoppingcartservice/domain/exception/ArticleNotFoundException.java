package com.resourceserver.emazonshoppingcartservice.domain.exception;

import com.resourceserver.emazonshoppingcartservice.domain.constants.ErrorMessagesConstants;

public class ArticleNotFoundException extends RuntimeException{

    public ArticleNotFoundException(Long articleId){
        super(String.format(ErrorMessagesConstants.ARTICLE_NOT_FOUND, articleId));
    }
}
