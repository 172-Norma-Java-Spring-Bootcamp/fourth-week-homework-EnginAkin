package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class OrderItem extends BaseExtendedModel {


    @OneToOne(cascade = CascadeType.ALL)
    private OrderDetail orderDetail;

    @OneToOne
    private Basket basket;

}
