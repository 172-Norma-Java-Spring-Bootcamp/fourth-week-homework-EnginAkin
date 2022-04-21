package com.tr.shopping.service.concretes;

import com.tr.shopping.service.abstracts.BankService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class BankServiceAdapter implements BankService {
    @Override
    public Boolean addPayment(BigDecimal accountNo, String expiry, String paymentType, String provider)  {
        return true; // payment successfull senario
    }
}
