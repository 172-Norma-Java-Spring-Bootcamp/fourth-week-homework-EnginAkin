package com.tr.shopping.core.converter.abstracts;

import com.tr.shopping.core.model.dto.CustomerCouponDto;
import com.tr.shopping.entity.CustomerCoupon;

public interface CustomerCouponConverterService {

    CustomerCoupon customerCouponDtoToCustomerCoupon(CustomerCouponDto customerCouponDto);
}
