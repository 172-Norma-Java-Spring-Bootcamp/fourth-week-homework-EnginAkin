package com.tr.shopping.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Customer extends BaseExtendedModel {

    private String username;
    private String email;
    private Long identity;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String password;
    private Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerAddress customerAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Basket basket;

    @OneToMany(mappedBy = "customer")
    private List<CustomerCoupon> coupons;

    public void addCouponCustomer(CustomerCoupon customerCoupon){
        if (coupons == null) {
            coupons = new ArrayList<>();
        }
        coupons.add(customerCoupon);


    }

}
