package com.tr.shopping.core.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductInventoryResponse {
    private BigDecimal quantity=BigDecimal.ZERO;
}
