package com.tr.shopping.core.exception;


import com.tr.shopping.core.constant.CustomerResponseMessage;
import org.springframework.http.HttpStatus;

public class OrderCannotFoundException extends GeneralException {

    public OrderCannotFoundException(){
        super(CustomerResponseMessage.ORDER_CANNOT_FOUND_EXCEPTİON);
    }
    public OrderCannotFoundException(String message, HttpStatus status){
        super(message,status);
    }
    public OrderCannotFoundException(String message){
        super(message);
    }
}
