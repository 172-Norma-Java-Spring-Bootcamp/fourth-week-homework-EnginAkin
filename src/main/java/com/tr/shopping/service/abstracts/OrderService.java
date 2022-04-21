package com.tr.shopping.service.abstracts;


import com.tr.shopping.core.model.dto.CustomerPaymentVerifyDto;
import com.tr.shopping.core.model.dto.OrderItemDto;
import com.tr.shopping.core.response.GeneralResponse;

public interface OrderService {

    GeneralResponse createOrder(OrderItemDto orderItemDto);

    GeneralResponse getCustomerOrderById(long customerId);

    GeneralResponse verifyCustomerOrder(long customerId, CustomerPaymentVerifyDto customerPaymentVerifyDto);
}
