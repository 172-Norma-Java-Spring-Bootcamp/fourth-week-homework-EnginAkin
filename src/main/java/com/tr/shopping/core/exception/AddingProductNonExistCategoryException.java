package com.tr.shopping.core.exception;


import org.springframework.http.HttpStatus;
import com.tr.shopping.core.constant.CategoryResponseMessage;

public class AddingProductNonExistCategoryException extends GeneralException {

    public AddingProductNonExistCategoryException(){
        super(CategoryResponseMessage.CATEGORY_NOT_FOUND_EXCEPTION);
    }
    public AddingProductNonExistCategoryException(String message, HttpStatus status){
        super(message,status);
    }
    public AddingProductNonExistCategoryException(String message){
        super(message);
    }
}
