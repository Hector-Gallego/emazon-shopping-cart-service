package com.resourceserver.emazonshoppingcartservice.configuration.exception.exceptionhandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomErrorResponse {

    private Integer status;
    private String message;
    private List<String> errors;
    private LocalDateTime timestamp;
}