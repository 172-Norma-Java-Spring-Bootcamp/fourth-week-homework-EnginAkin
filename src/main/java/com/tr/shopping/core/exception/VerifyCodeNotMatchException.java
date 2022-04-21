package com.tr.shopping.core.exception;


import com.tr.shopping.core.constant.CustomerResponseMessage;
import org.springframework.http.HttpStatus;

public class VerifyCodeNotMatchException extends GeneralException {

    public VerifyCodeNotMatchException(){
        super(CustomerResponseMessage.CUSTOMER_VERIFY_CODE_NOT_MATCH_EXCEPTION);
    }
    public VerifyCodeNotMatchException(String message, HttpStatus status){
        super(message,status);
    }
    public VerifyCodeNotMatchException(String message){
        super(message);
    }
}
