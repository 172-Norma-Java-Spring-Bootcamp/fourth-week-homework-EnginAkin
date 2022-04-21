package com.tr.shopping.core.exception;

import org.springframework.http.HttpStatus;

public class GeneralException extends RuntimeException{
    private String message;
    private HttpStatus statusCode;

    public GeneralException(String message,HttpStatus statusCode){
        super(message);
        this.statusCode=statusCode;
    }
    public  GeneralException(String message){
        super(message);
    }
    public GeneralException(){};
}
