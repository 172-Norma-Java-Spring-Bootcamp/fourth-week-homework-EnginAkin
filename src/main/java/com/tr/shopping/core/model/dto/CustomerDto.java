package com.tr.shopping.core.model.dto;

import lombok.Data;
import com.tr.shopping.entity.Gender;

@Data
public class CustomerDto{
    private String username;
    private String email;
    private Gender gender;
    private Long identity;
    private String password;
    private CustomerAddressDto customerAddress;



}
