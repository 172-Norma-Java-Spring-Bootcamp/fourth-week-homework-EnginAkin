package com.tr.shopping.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CustomerCouponDto {

    private String code;

    private BigDecimal percentage;


    private CategoryDto category;

}
