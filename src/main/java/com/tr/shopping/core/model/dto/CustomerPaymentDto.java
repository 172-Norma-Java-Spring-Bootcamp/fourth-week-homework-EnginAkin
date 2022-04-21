package com.tr.shopping.core.model.dto;

import com.tr.shopping.entity.BaseModel;
import com.tr.shopping.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CustomerPaymentDto{

    private String paymentType;

    private String provider;

    private BigDecimal accountNo;

    private String expiry;

}
