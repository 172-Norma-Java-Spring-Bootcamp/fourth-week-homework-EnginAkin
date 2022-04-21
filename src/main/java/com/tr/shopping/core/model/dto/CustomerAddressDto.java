package com.tr.shopping.core.model.dto;

import lombok.Data;

@Data
public class CustomerAddressDto
{

    private String phoneNumber;
    private String country;
    private String city;
    private String postalCode;
    private String description;
}
