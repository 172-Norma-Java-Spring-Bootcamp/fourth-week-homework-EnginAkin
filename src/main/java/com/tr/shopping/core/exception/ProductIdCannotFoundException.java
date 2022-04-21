package com.tr.shopping.core.exception;


import org.springframework.http.HttpStatus;
import com.tr.shopping.core.constant.ProductResponseMessage;

public class ProductIdCannotFoundException extends GeneralException {

    public ProductIdCannotFoundException(){
        super(ProductResponseMessage.PRODUCT_ID_CANNOT_FOUND);
    }
    public ProductIdCannotFoundException(String message, HttpStatus status){
        super(message,status);
    }
    public ProductIdCannotFoundException(String message){
        super(message);
    }
}
