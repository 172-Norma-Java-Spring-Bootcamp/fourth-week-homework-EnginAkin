package com.tr.shopping.core.converter.concretes;

import com.tr.shopping.core.converter.abstracts.PaymentConverterService;
import com.tr.shopping.core.model.dto.CustomerPaymentDto;
import com.tr.shopping.entity.Customer;
import com.tr.shopping.entity.CustomerPayment;
import org.springframework.stereotype.Service;

@Service
public class PaymentConverterServiceImpl implements PaymentConverterService {

    @Override
    public CustomerPayment customerPaymentDtoToCustomerPayment(CustomerPaymentDto customerPaymentDto, Customer customer) {
        CustomerPayment customerPayment=new CustomerPayment();
        customerPayment.setCustomer(customer);
        customerPayment.setPaymentType(customerPaymentDto.getPaymentType());
        customerPayment.setAccountNo(customerPaymentDto.getAccountNo());
        customerPayment.setExpiry(customerPaymentDto.getExpiry());
        customerPayment.setProvider(customerPaymentDto.getProvider());
        return customerPayment;
    }
}
