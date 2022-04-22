package com.tr.shopping.core.converter.concretes;

import com.tr.shopping.core.converter.abstracts.*;
import com.tr.shopping.entity.CustomerCoupon;
import com.tr.shopping.entity.CustomerPayment;
import com.tr.shopping.entity.OrderItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class ConverterService {
    private final CustomerConverterService customerConverterService;
    private final ProductConverterService productConverterService;
    private final BasketConverterService basketConverterService;
    private final CategoryConverterService categoryConverterService;
    private final PaymentConverterService paymentConverterService;
    private final OrderDetailConverterService orderDetailConverterService;
    private final CustomerCouponConverterService customerCouponConverterService;
}
