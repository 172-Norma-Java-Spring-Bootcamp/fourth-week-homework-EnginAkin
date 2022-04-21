package com.tr.shopping.core.model.response;

import com.tr.shopping.entity.Customer;
import com.tr.shopping.entity.OrderStatus;
import com.tr.shopping.entity.PaymentDetail;
import com.tr.shopping.entity.ShipMethod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OrderDetailResponse {

    private Date creationDate;

    private String shipMethod;

    private BigDecimal totalAmount;

    private String orderStatus;
}
