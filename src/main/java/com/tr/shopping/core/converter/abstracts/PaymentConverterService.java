package com.tr.shopping.core.converter.abstracts;

import com.tr.shopping.core.model.dto.CustomerPaymentDto;
import com.tr.shopping.entity.Customer;
import com.tr.shopping.entity.CustomerPayment;

public interface PaymentConverterService {
    CustomerPayment customerPaymentDtoToCustomerPayment(CustomerPaymentDto customerPaymentDto, Customer customer);
}
