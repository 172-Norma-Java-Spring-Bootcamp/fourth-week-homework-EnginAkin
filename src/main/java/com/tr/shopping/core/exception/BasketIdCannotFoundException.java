package com.tr.shopping.core.exception;


import com.tr.shopping.core.constant.CustomerResponseMessage;
import org.springframework.http.HttpStatus;

public class BasketIdCannotFoundException extends GeneralException {

    public BasketIdCannotFoundException(){
        super(CustomerResponseMessage.BASKET_ID_CANNOT_FOUND_EXCEPTION);
    }
    public BasketIdCannotFoundException(String message, HttpStatus status){
        super(message,status);
    }
    public BasketIdCannotFoundException(String message){
        super(message);
    }
}
