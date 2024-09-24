package com.resourceserver.emazonshoppingcartservice.ports.driving.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomApiResponse{

    private Integer status;
    private String message;
    private LocalDateTime timestamp;
}
