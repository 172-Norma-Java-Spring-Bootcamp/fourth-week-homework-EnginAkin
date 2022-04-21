package com.tr.shopping.core.model.dto;
import com.tr.shopping.entity.Basket;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDto {
    @NotNull
    private Long basketId;
    @NotNull
    private Long customerId;
}
