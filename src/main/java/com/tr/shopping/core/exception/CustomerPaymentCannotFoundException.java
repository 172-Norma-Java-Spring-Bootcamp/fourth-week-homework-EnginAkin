package com.tr.shopping.core.exception;


import com.tr.shopping.core.constant.CustomerResponseMessage;
import org.springframework.http.HttpStatus;

public class CustomerPaymentCannotFoundException extends GeneralException {

    public CustomerPaymentCannotFoundException(){
        super(CustomerResponseMessage.CUSTOMER_PAYMENT_CANNOT_FOUND_EXCEPTION);
    }
    public CustomerPaymentCannotFoundException(String message, HttpStatus status){
        super(message,status);
    }
    public CustomerPaymentCannotFoundException(String message){
        super(message);
    }
}
