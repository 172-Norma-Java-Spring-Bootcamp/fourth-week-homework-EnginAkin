package com.tr.shopping.core.model.dto;

import com.tr.shopping.entity.BaseModel;
import com.tr.shopping.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CustomerPaymentDto{

    @NotNull
    private String paymentType;
    @NotNull
    private String provider;

    @NotNull
    private BigDecimal accountNo;

    @NotNull
    private String expiry;

}
