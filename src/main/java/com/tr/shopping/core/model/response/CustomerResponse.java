package com.tr.shopping.core.model.response;

import lombok.Getter;
import lombok.Setter;
import com.tr.shopping.entity.Gender;
import com.tr.shopping.core.model.dto.CustomerAddressDto;

@Getter
@Setter
public class CustomerResponse {
    private String username;
    private String email;
    private Gender gender;
    private CustomerAddressDto customerAddress;
}
