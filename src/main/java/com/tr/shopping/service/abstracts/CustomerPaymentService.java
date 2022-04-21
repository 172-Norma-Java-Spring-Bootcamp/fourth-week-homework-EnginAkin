package com.tr.shopping.service.abstracts;

import com.tr.shopping.core.response.GeneralResponse;

public interface CustomerPaymentService {
    GeneralResponse getCustomerVerifyCode(long customerId);

}
