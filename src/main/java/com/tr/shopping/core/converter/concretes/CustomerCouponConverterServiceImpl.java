package com.tr.shopping.core.converter.concretes;

import com.tr.shopping.core.converter.abstracts.CustomerCouponConverterService;
import com.tr.shopping.core.model.dto.CategoryDto;
import com.tr.shopping.core.model.dto.CustomerCouponDto;
import com.tr.shopping.entity.Category;
import com.tr.shopping.entity.CustomerCoupon;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerCouponConverterServiceImpl implements CustomerCouponConverterService {
    @Override
    public CustomerCoupon customerCouponDtoToCustomerCoupon(CustomerCouponDto customerCouponDto) {
        CustomerCoupon customerCoupon=new CustomerCoupon();
        customerCoupon.setCreatedAt(new Date());
        customerCoupon.setCreatedBy("ENGIN AKIN");
        customerCoupon.setCode(customerCouponDto.getCode());
        customerCoupon.setPercentage(customerCouponDto.getPercentage());
        return customerCoupon;
    }
}
