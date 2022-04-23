package com.tr.shopping.service.abstracts;

import com.tr.shopping.entity.CustomerCoupon;
import com.tr.shopping.repository.CustomerCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCouponServiceImpl implements CustomerCouponService {
    private final CustomerCouponRepository customerCouponRepository;
    @Override
    public CustomerCoupon save(CustomerCoupon coupon) {
        return null;
    }
}
