package com.tr.shopping.core.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CustomerPaymentVerifyDto {

    @NotNull
    private String verifyCode;
}
