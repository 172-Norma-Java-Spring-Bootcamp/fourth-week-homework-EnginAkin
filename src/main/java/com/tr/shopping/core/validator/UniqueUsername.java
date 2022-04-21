package com.tr.shopping.core.validator;

import com.tr.shopping.core.constant.CustomerResponseMessage;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD,METHOD ,PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = { UsernameValidator.class })
public @interface UniqueUsername {


    String message() default CustomerResponseMessage.CUSTOMER_USERNAME_MUST_BE_UNIQE_EXCEPTION;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
