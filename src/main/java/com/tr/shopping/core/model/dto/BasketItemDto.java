package com.tr.shopping.core.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BasketItemDto {

    @NotNull
    private BigDecimal quantity;
    private BigDecimal discountPrice = BigDecimal.ZERO;
    private BigDecimal taxPrice = BigDecimal.ZERO;
    private BigDecimal shippingPrice = BigDecimal.ZERO;
    @NotNull
    private Long productId;

}
