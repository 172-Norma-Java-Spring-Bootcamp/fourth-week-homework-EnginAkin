package com.tr.shopping.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GeneralResponse {
    private String message;
    private Boolean isSuccessful;
}
