package com.resourceserver.emazonshoppingcartservice.ports.driving.controller;

import com.resourceserver.emazonshoppingcartservice.configuration.exception.exceptionhandle.CustomErrorResponse;
import com.resourceserver.emazonshoppingcartservice.configuration.openapi.constants.OpenApiConstants;
import com.resourceserver.emazonshoppingcartservice.domain.constants.SuccessMessagesConstants;
import com.resourceserver.emazonshoppingcartservice.domain.ports.api.AddItemToCartServicePort;
import com.resourceserver.emazonshoppingcartservice.ports.driving.dto.request.CartItemDto;
import com.resourceserver.emazonshoppingcartservice.ports.driving.dto.response.CustomApiResponse;
import com.resourceserver.emazonshoppingcartservice.ports.driving.mapper.CartItemMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/cart")
public class AddItemToCartController {


    private final AddItemToCartServicePort addItemToCartServicePort;
    private final CartItemMapper cartItemMapper;
    public AddItemToCartController(AddItemToCartServicePort addItemToCartServicePort, CartItemMapper cartItemMapper) {
        this.addItemToCartServicePort = addItemToCartServicePort;
        this.cartItemMapper = cartItemMapper;
    }


    @Operation(summary = OpenApiConstants.OPEN_API_ADD_ITEM_SHOPPING_CART_SUMMARY,
            description = OpenApiConstants.OPEN_API_ADD_ITEM_SHOPPING_CART_DESCRIPTION)

    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_200,
                    description = OpenApiConstants.CART_ITEM_ADDED,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_400,
                    description = OpenApiConstants.INVALID_INPUT,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_500,
                    description = OpenApiConstants.OPENAPI_INTERNAL_SERVER_ERROR,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @PostMapping
    ResponseEntity<CustomApiResponse> addItem(@Valid @RequestBody CartItemDto cartItemDto){

        addItemToCartServicePort.addItemToCartShopping(cartItemMapper.toDomain(cartItemDto));
        CustomApiResponse response = new CustomApiResponse(
                HttpStatus.OK.value(),
                SuccessMessagesConstants.ARTICLE_ADDED_TO_CART_SUCCESS,
                LocalDateTime.now()
        );
        return ResponseEntity.ok().body(response);
    }
}
