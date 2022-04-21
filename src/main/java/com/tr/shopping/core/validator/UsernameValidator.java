package com.tr.shopping.core.validator;

import lombok.RequiredArgsConstructor;
import com.tr.shopping.core.model.dto.CustomerDto;
import com.tr.shopping.repository.CustomerRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<UniqueUsername, CustomerDto> {
    private final CustomerRepository customerRepository;


    @Override
    public boolean isValid(CustomerDto customerDto, ConstraintValidatorContext context) {
        return !customerRepository.existsCustomerByUsername(customerDto.getUsername());
    }
}
