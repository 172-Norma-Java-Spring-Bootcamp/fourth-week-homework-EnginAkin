package com.tr.shopping.core.exception;


import org.springframework.http.HttpStatus;
import com.tr.shopping.core.constant.CustomerResponseMessage;

public class CustomerBasketNullException extends GeneralException {

    public CustomerBasketNullException(){
        super(CustomerResponseMessage.CUSTOMER_BASKET_CANNOT_FOUND_EXCEPTION);
    }
    public CustomerBasketNullException(String message, HttpStatus status){
        super(message,status);
    }
    public CustomerBasketNullException(String message){
        super(message);
    }
}
