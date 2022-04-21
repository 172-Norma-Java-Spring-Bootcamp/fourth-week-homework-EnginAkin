package com.tr.shopping.core.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralDataResponse<T> extends GeneralResponse {
    private T data;

    public GeneralDataResponse(String message, Boolean isSuccessful, T data) {
        super(message, isSuccessful);
        this.data = data;
    }


    public GeneralDataResponse(T data) {
        this.data = data;
    }
}
