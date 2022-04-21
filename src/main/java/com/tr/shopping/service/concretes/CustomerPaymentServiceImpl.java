package com.tr.shopping.service.concretes;

import com.tr.shopping.core.response.GeneralDataResponse;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.repository.CustomerPaymentRepository;
import com.tr.shopping.service.abstracts.CustomerPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerPaymentServiceImpl implements CustomerPaymentService {
    private final CustomerPaymentRepository customerPaymentRepository;
    @Override
    public GeneralResponse getCustomerVerifyCode(long customerId) {
        System.out.println("gelen değer "+customerId);
        return new GeneralDataResponse<>(customerPaymentRepository.getCustomerPaymentByCustomerId(customerId).getPaymentVerifyCode());
    }
}
