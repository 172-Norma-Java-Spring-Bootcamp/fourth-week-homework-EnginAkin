package com.tr.shopping.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.tr.shopping.core.response.GeneralErrorResponse;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(AddingProductNonExistCategoryException.class)
    public GeneralErrorResponse handlerCategoryNotFoundException(AddingProductNonExistCategoryException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public GeneralErrorResponse handlerConstaintViolationException(ConstraintViolationException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerBasketCannotFoundException.class)
    public GeneralErrorResponse handlerCustomerBasketCannotFoundException(CustomerBasketCannotFoundException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerPaymentCannotFoundException.class)
    public GeneralErrorResponse handlerCustomerPaymentCannotFoundException(CustomerPaymentCannotFoundException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BasketIdCannotFoundException.class)
    public GeneralErrorResponse handlerBasketIdCannotFoundException(BasketIdCannotFoundException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerIdCannotFountException.class)
    public GeneralErrorResponse handlerCustomerIdCannotFoundException(CustomerIdCannotFountException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderCannotFoundException.class)
    public GeneralErrorResponse handlerOrderCannotFoundException(OrderCannotFoundException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerifyCodeNotMatchException.class)
    public GeneralErrorResponse handlerVerifyCodeNotMatchException(VerifyCodeNotMatchException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerDeletedException.class)
    public GeneralErrorResponse handlerCustomerAlreadyDeletedException(CustomerDeletedException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerBasketNullException.class)
    public GeneralErrorResponse handlerBasketNullException(CustomerBasketNullException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryCannotFoundException.class)
    public GeneralErrorResponse handlerCategoryCannotFoundException(CategoryCannotFoundException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductIdCannotFoundException.class)
    public GeneralErrorResponse handlerProductIdCannotFoundException(ProductIdCannotFoundException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerBasketQuantityException.class)
    public GeneralErrorResponse handlerCustomerBasketQuantityException(CustomerBasketQuantityException exception){
        return new GeneralErrorResponse(exception.getMessage());
    }
}
