package com.tr.shopping.core.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BasketResponse {
    private BigDecimal price;
    private BigDecimal discountPrice;
    private BigDecimal taxPrice;
    private BigDecimal shippingPrice;
    private BigDecimal totalPrice;
}
