package com.tr.shopping.controller;

import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.service.abstracts.CustomerPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CustomerPaymentController {

    private final CustomerPaymentService customerPaymentService;

    @GetMapping("customers/{customerId}/payments/code")
    public GeneralResponse getCustomerVerifyCode(@PathVariable("customerId") int customerId){
        return customerPaymentService.getCustomerVerifyCode(customerId);
    }
}
