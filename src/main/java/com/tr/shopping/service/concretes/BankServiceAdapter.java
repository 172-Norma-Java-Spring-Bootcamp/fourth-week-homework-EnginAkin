package com.tr.shopping.service.concretes;

import com.tr.shopping.service.abstracts.BankService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BankServiceAdapter implements BankService {
    @Override
    public Boolean addPayment(BigDecimal accountNo, String expiry, String paymentType, String provider)  {
        log.info("payment successfull acocount no : {} ",accountNo);
        return true;
    }
}
