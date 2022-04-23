package com.tr.shopping.service.abstracts;

import com.tr.shopping.core.exception.CustomerIdCannotFountException;
import com.tr.shopping.core.exception.ProductIdCannotFoundException;
import com.tr.shopping.core.model.dto.CustomerCouponDto;
import com.tr.shopping.core.model.dto.CustomerPaymentDto;
import com.tr.shopping.core.response.GeneralDataResponse;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.model.dto.BasketItemDto;
import com.tr.shopping.core.model.dto.CustomerDto;
import com.tr.shopping.entity.Customer;

public interface CustomerService {
    GeneralResponse createCustomer(CustomerDto customerDto);

    GeneralResponse getCustomerAddress(long id);

    GeneralDataResponse<Customer> getCustomerById(long id);

    GeneralResponse deleteCustomerById(long customerId);

    GeneralResponse addBasketItemToCustomerBasket(BasketItemDto basketItemDto, long customerId) throws ProductIdCannotFoundException, CustomerIdCannotFountException;

    GeneralResponse getCustomerBasket(long customerId);

    GeneralResponse getCustomerProduct(long customerId);

    GeneralResponse getAllCustomer();

    GeneralResponse addCustomerPayment(CustomerPaymentDto customerPaymentDto,long customerId);

    GeneralDataResponse getCustomerVerifyCode(long id);

    GeneralResponse createCouponForCustomer(long customerId, CustomerCouponDto customerCouponDto);
}
