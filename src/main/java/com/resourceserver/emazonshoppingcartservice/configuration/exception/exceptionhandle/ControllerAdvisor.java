package com.resourceserver.emazonshoppingcartservice.configuration.exception.exceptionhandle;


import com.resourceserver.emazonshoppingcartservice.configuration.exception.constants.ExceptionsConstants;
import com.resourceserver.emazonshoppingcartservice.configuration.feign.CustomFeignException;
import com.resourceserver.emazonshoppingcartservice.domain.constants.ErrorMessagesConstants;
import com.resourceserver.emazonshoppingcartservice.domain.exception.ArticleNotFoundException;
import com.resourceserver.emazonshoppingcartservice.domain.exception.CategoryLimitExceededException;
import com.resourceserver.emazonshoppingcartservice.domain.exception.InsufficientStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {


    @ExceptionHandler(InvalidBearerTokenException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidBearerTokenException(InvalidBearerTokenException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED.value(), Collections.emptyList());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccessDeniedExceptions(AccessDeniedException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.FORBIDDEN.value(), Collections.emptyList());
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<CustomErrorResponse> handleInsufficientAuthenticationException(InsufficientAuthenticationException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED.value(), Collections.emptyList());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<CustomErrorResponse> handleInsufficientStockException(InsufficientStockException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), Collections.emptyList());
    }
    @ExceptionHandler(CategoryLimitExceededException.class)
    public ResponseEntity<CustomErrorResponse> handleInsufficientStockException(CategoryLimitExceededException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), Collections.emptyList());
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<CustomErrorResponse> handleConnectException(ConnectException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), Collections.emptyList());
    }

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomFeignException(CustomFeignException exception) {
        return buildErrorResponse(exception.getMessage(), exception.getStatus(), Collections.emptyList());
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleArticleNotFoundException(ArticleNotFoundException exception) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), Collections.emptyList());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomRuntimeException(RuntimeException exception) {

        log.error(ExceptionsConstants.LOG_EXCEPTION_ERROR_MESSAGE, exception.getMessage(), exception.getClass().getName());
        return buildErrorResponse(ExceptionsConstants.UNEXPECTED_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR.value(), Collections.emptyList());

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            @Nullable HttpHeaders headers,
            @Nullable HttpStatusCode status,
            @Nullable WebRequest request) {

        List<String> errorList = exception
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        CustomErrorResponse response = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ErrorMessagesConstants.INVALID_FIELDS,
                errorList,
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<CustomErrorResponse> buildErrorResponse(String message, Integer status, List<String> errors) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                status,
                message,
                errors,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(customErrorResponse, HttpStatusCode.valueOf(status));
    }
}
