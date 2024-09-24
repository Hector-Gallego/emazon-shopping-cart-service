package com.resourceserver.emazonshoppingcartservice.ports.driving.dto.request;

import com.resourceserver.emazonshoppingcartservice.domain.constants.ErrorMessagesConstants;
import com.resourceserver.emazonshoppingcartservice.domain.constants.ValuesConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartItemDto {

    @NotNull(message = ErrorMessagesConstants.ARTICLE_ID_NOT_NULL)
    @Positive(message = ErrorMessagesConstants.ARTICLE_ID_POSITIVE)
    private Long articleId;

    @NotNull(message = ErrorMessagesConstants.QUANTITY_NOT_NULL)
    @Min(value = ValuesConstants.MIN_QUANTITY_VALUE, message = ErrorMessagesConstants.QUANTITY_MIN_VALUE)
    private Integer quantity;


}
