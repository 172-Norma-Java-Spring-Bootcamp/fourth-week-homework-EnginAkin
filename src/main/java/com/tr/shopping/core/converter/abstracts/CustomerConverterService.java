package com.tr.shopping.core.converter.abstracts;

import com.tr.shopping.entity.BasketItem;
import com.tr.shopping.entity.Customer;
import com.tr.shopping.core.model.dto.BasketItemDto;
import com.tr.shopping.core.model.dto.CustomerDto;
import com.tr.shopping.core.model.response.CustomerResponse;

public interface CustomerConverterService {


    Customer customerDtoToCustomer(CustomerDto customerDto);

    BasketItem basketItemDtoToBasket(BasketItemDto basketItemDto, Long customerId);

    CustomerResponse customerToCustomerResponse(Customer customer);
}
