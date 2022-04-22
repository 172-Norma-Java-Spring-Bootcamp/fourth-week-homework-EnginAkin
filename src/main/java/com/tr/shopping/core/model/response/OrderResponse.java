package com.tr.shopping.core.model.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponse {

    private String orderStatus;
    private String paymentStatus;
    private BigDecimal totalAmount;
    private BigDecimal paymentAmount;
    private BigDecimal applyedDiscount;

}
