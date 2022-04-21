package com.tr.shopping.core.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralErrorResponse extends GeneralResponse {


    public GeneralErrorResponse(String message) {
        super(message, false);
    }

}
