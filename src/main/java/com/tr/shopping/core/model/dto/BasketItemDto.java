package com.tr.shopping.core.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasketItemDto {

    private BigDecimal quantity;
    private BigDecimal discountPrice = BigDecimal.ZERO;
    private BigDecimal taxPrice = BigDecimal.ZERO;
    private BigDecimal shippingPrice = BigDecimal.ZERO;
    private Long productId;

}
