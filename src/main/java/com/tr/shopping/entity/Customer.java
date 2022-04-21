package com.tr.shopping.entity;


import lombok.Data;

import javax.persistence.*;

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



}
