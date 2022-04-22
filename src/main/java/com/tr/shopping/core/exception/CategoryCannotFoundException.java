package com.tr.shopping.core.exception;


import com.tr.shopping.core.constant.CustomerResponseMessage;
import org.springframework.http.HttpStatus;

public class CategoryCannotFoundException extends GeneralException {

    public CategoryCannotFoundException(){
        super(CustomerResponseMessage.CATEGORY_CANNOT_FOUND_EXCEPTION);
    }
    public CategoryCannotFoundException(String message, HttpStatus status){
        super(message,status);
    }
    public CategoryCannotFoundException(String message){
        super(message);
    }
}
