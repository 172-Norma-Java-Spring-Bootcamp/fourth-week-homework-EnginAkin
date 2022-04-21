package com.tr.shopping.controller;

import com.tr.shopping.core.model.dto.CustomerPaymentDto;
import com.tr.shopping.core.model.dto.CustomerPaymentVerifyDto;
import com.tr.shopping.service.abstracts.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.model.dto.BasketItemDto;
import com.tr.shopping.core.model.dto.CustomerDto;
import com.tr.shopping.service.abstracts.CustomerService;
import com.tr.shopping.core.validator.UniqueUsername;

import javax.validation.constraints.Min;

@RestController
@RequestMapping(path = "api/v1/customers")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CustomerController {

    private final CustomerService customerService;



    @PostMapping(path = "/create")
    public GeneralResponse createCustomer(@Validated @RequestBody @UniqueUsername CustomerDto customerDto){
        return customerService.createCustomer(customerDto);

    }
    @PostMapping(path = "/{customerId}/payment")
    public GeneralResponse addCustomerPayment(@PathVariable(value = "customerId") int customerId,@RequestBody CustomerPaymentDto customerPaymentDto){
        System.out.println("gelen değer "+customerId);
        return customerService.addCustomerPayment(customerPaymentDto, (long) customerId);

    }


    @GetMapping(path = "/{id}/code")
    public GeneralResponse getCustomerVerifyCode(@PathVariable("id") int customerId){
        return customerService.getCustomerVerifyCode(customerId);
    }

    @GetMapping(path = "{id}/address")
    public GeneralResponse getCustomerAddress(@PathVariable(value = "id") @Min(0) int id){
        return customerService.getCustomerAddress((long) id);
    }
    @GetMapping(path = "{id}")
    public GeneralResponse getCustomerWithId(@PathVariable(value = "id") @Min(0) int id){
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public GeneralResponse getCustomers(){
        return customerService.getAllCustomer();
    }


    @PutMapping(path = "/{id}/delete")
    public GeneralResponse deleteCustomerById(@PathVariable("id") @Min(0) int customerId){
        return customerService.deleteCustomerById((long) customerId);
    }

    @PostMapping(path = "/{customerId}/basket")
    public GeneralResponse addBasketItemToBasket(@PathVariable("customerId") int customerId, @RequestBody BasketItemDto basketItemDto){
        return customerService.addBasketItemToCustomerBasket(basketItemDto,customerId);
    }
    @GetMapping(path = "/{customerId}/basket")
    public GeneralResponse getBasketItem(@PathVariable("customerId") int customerId){
        return customerService.getCustomerBasket((long)customerId);
    }
    @GetMapping(path = "/{customerId}/products")
    public GeneralResponse getCustomerProducts(@PathVariable("customerId") int customerId){
        return customerService.getCustomerProduct((long)customerId);
    }



}
