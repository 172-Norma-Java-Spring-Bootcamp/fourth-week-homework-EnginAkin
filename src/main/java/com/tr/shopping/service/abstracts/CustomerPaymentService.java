package com.tr.shopping.service.abstracts;

import com.tr.shopping.entity.CustomerCoupon;
import com.tr.shopping.entity.CustomerPayment;

public interface CustomerPaymentService {
    CustomerPayment save(CustomerPayment customerPayment);
    CustomerPayment getCustomerPaymentByCustomerId(Long customerPaymentId);

}
