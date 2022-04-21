package com.tr.shopping.core.exception;


import org.springframework.http.HttpStatus;
import com.tr.shopping.core.constant.CustomerResponseMessage;

public class CustomerDeletedException extends GeneralException {

    public CustomerDeletedException(){
        super(CustomerResponseMessage.CUSTOMER_ALREADY_DELETED);
    }
    public CustomerDeletedException(String message, HttpStatus status){
        super(message,status);
    }
    public CustomerDeletedException(String message){
        super(message);
    }
}
