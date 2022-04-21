package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class OrderDetail extends BaseModel {

    @OneToOne
    private  Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    private PaymentDetail paymentDetail;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToOne(cascade = CascadeType.ALL)
    private ShipMethod shipMethod;

    private BigDecimal totalAmount;

    @OneToOne(cascade = CascadeType.ALL)
    private OrderStatus orderStatus;

}
