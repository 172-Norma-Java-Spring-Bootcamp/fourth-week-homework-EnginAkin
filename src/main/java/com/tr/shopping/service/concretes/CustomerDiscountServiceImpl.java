package com.tr.shopping.service.concretes;

import com.tr.shopping.entity.CustomerCoupon;
import com.tr.shopping.service.abstracts.CustomerDiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerDiscountServiceImpl implements CustomerDiscountService {
    @Override
    public BigDecimal applyDiscount(CustomerCoupon coupon, BigDecimal totalAmount) {
        BigDecimal percentage=coupon.getPercentage().divide(BigDecimal.valueOf(100));// % 20 -> 0.2
        return totalAmount.subtract(totalAmount.multiply(percentage));
    }
}
