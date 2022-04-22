package com.tr.shopping.controller;

import com.tr.shopping.core.model.dto.CustomerPaymentVerifyDto;
import com.tr.shopping.core.model.dto.OrderItemDto;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.service.abstracts.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    private GeneralResponse createOrder(@RequestBody OrderItemDto orderItemDto) {
        return orderService.createOrder(orderItemDto);
    }

    @PostMapping("{customerId}/verify")
    private GeneralResponse verifyOrder(@PathVariable("customerId") int customerId, @RequestBody CustomerPaymentVerifyDto customerPaymentVerifyDto) {
        return orderService.verifyCustomerOrder(customerId,customerPaymentVerifyDto);
    }

    @GetMapping("/{customerId}")
    private GeneralResponse getCustomerOrder(@PathVariable("customerId") int customerId){
        return orderService.getCustomerOrderById(customerId);
    }



}
