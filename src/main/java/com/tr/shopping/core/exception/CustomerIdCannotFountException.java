package com.tr.shopping.core.exception;


import org.springframework.http.HttpStatus;
import com.tr.shopping.core.constant.CustomerResponseMessage;

public class CustomerIdCannotFountException extends GeneralException {

    public CustomerIdCannotFountException(){
        super(CustomerResponseMessage.CUSTOMER_ID_CANNOT_FOUND_EXCEPTION);
    }
    public CustomerIdCannotFountException(String message, HttpStatus status){
        super(message,status);
    }
    public CustomerIdCannotFountException(String message){
        super(message);
    }
}
