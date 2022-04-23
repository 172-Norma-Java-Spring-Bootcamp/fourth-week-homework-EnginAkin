package com.tr.shopping.service.concretes;

import com.tr.shopping.entity.CustomerCoupon;
import com.tr.shopping.service.abstracts.CustomerDiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class CustomerDiscountServiceImpl implements CustomerDiscountService {
    @Override
    public BigDecimal applyDiscount(CustomerCoupon coupon, BigDecimal totalAmount) {
        BigDecimal percentage=coupon.getPercentage().divide(BigDecimal.valueOf(100));
        log.info("apple discount with customer coupon  discount amount percentage:{}", percentage);
        return totalAmount.subtract(totalAmount.multiply(percentage));
    }
}
