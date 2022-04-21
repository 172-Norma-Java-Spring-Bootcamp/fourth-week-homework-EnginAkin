package com.tr.shopping.core.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralSuccessfullResponse extends GeneralResponse {
    public GeneralSuccessfullResponse(String message) {
        super(message, true);
    }

}
