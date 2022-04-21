package com.tr.shopping.service.abstracts;

import com.tr.shopping.core.exception.CustomerIdCannotFountException;
import com.tr.shopping.core.exception.ProductIdCannotFoundException;
import com.tr.shopping.core.model.dto.CustomerPaymentDto;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.model.dto.BasketItemDto;
import com.tr.shopping.core.model.dto.CustomerDto;

public interface CustomerService {
    GeneralResponse createCustomer(CustomerDto customerDto);

    GeneralResponse getCustomerAddress(Long id);

    GeneralResponse getCustomerById(long id);

    GeneralResponse deleteCustomerById(long customerId);

    GeneralResponse addBasketItemToCustomerBasket(BasketItemDto basketItemDto, long customerId) throws ProductIdCannotFoundException, CustomerIdCannotFountException;

    GeneralResponse getCustomerBasket(long customerId);

    GeneralResponse getCustomerProduct(long customerId);

    GeneralResponse getAllCustomer();

    GeneralResponse addCustomerPayment(CustomerPaymentDto customerPaymentDto,Long customerId);

    GeneralResponse getCustomerVerifyCode(long id);

}
