package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class CustomerPayment extends BaseModel{

    @OneToOne
    private Customer customer;

    private String paymentType;

    private String provider;

    private BigDecimal accountNo;

    private String expiry;

    private String paymentVerifyCode;


}
