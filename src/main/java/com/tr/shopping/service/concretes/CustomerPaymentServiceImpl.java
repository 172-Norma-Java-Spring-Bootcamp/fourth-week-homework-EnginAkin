package com.tr.shopping.service.concretes;

import com.tr.shopping.entity.CustomerPayment;
import com.tr.shopping.repository.CustomerCouponRepository;
import com.tr.shopping.repository.CustomerPaymentRepository;
import com.tr.shopping.service.abstracts.CustomerPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerPaymentServiceImpl implements CustomerPaymentService {
    private final CustomerPaymentRepository customerPaymentRepository;

    @Override
    public CustomerPayment save(CustomerPayment customerPayment) {
        return customerPaymentRepository.save(customerPayment);
    }

    @Override
    public CustomerPayment getCustomerPaymentByCustomerId(Long customerPaymentId) {
        return customerPaymentRepository.getCustomerPaymentByCustomerId(customerPaymentId);
    }
}
