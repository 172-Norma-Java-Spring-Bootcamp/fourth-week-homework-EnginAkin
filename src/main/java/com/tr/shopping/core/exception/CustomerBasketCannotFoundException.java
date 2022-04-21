package com.tr.shopping.core.exception;


import com.tr.shopping.core.constant.CustomerResponseMessage;
import org.springframework.http.HttpStatus;

public class CustomerBasketCannotFoundException extends GeneralException {

    public CustomerBasketCannotFoundException(){
        super(CustomerResponseMessage.CUSTOMER_BASKET_NO_BELONG_EXCEPTION);
    }
    public CustomerBasketCannotFoundException(String message, HttpStatus status){
        super(message,status);
    }
    public CustomerBasketCannotFoundException(String message){
        super(message);
    }
}
