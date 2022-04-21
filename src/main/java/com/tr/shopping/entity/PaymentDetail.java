package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class PaymentDetail extends BaseModel{

    @OneToOne(cascade = CascadeType.ALL)
    private PaymentStatus status;

    private BigDecimal amount;
    private String provider;


}
