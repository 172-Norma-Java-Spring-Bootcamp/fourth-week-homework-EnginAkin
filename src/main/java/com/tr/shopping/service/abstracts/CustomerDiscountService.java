package com.tr.shopping.service.abstracts;

import com.tr.shopping.entity.CustomerCoupon;

import java.math.BigDecimal;

public interface CustomerDiscountService {
    BigDecimal applyDiscount(CustomerCoupon coupon,BigDecimal totalAmount);
}
