package com.tr.shopping.core.exception;


import org.springframework.http.HttpStatus;
import com.tr.shopping.core.constant.CustomerResponseMessage;

public class CustomerBasketQuantityException extends GeneralException {

    public CustomerBasketQuantityException(){
        super(CustomerResponseMessage.CUSTOMER_BASKET_ADDED_PRODUCT_QUANTITY_EXCEPTION);
    }
    public CustomerBasketQuantityException(String message, HttpStatus status){
        super(message,status);
    }
    public CustomerBasketQuantityException(String message){
        super(message);
    }
}
