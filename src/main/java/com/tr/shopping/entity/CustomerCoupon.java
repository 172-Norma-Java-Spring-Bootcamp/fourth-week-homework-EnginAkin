package com.tr.shopping.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CustomerCoupon extends BaseExtendedModel{

    private String code;
    private BigDecimal percentage;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;





}
