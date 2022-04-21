package com.tr.shopping.core.converter.abstracts;

import com.tr.shopping.entity.Basket;
import com.tr.shopping.core.model.response.BasketResponse;

public interface BasketConverterService {
    BasketResponse basketToBasketResponse(Basket basket);
}
