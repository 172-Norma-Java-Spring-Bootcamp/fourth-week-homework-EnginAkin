package com.tr.shopping.service.abstracts;

import com.tr.shopping.entity.Customer;

import java.math.BigDecimal;

public interface BankService {
    public Boolean addPayment(BigDecimal accountNo,String expiry,String paymentType,String provider) throws InterruptedException;


}
