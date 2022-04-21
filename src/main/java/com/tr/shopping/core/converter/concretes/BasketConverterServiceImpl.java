package com.tr.shopping.core.converter.concretes;

import org.springframework.stereotype.Service;
import com.tr.shopping.core.converter.abstracts.BasketConverterService;
import com.tr.shopping.entity.Basket;
import com.tr.shopping.core.model.response.BasketResponse;

@Service
public class BasketConverterServiceImpl implements BasketConverterService {
    @Override
    public BasketResponse basketToBasketResponse(Basket basket) {
        BasketResponse basketResponse =new BasketResponse();
        basketResponse.setDiscountPrice(basket.getDiscountPrice());
        basketResponse.setPrice(basket.getPrice());
        basketResponse.setTaxPrice(basket.getTaxPrice());
        basketResponse.setShippingPrice(basket.getShippingPrice());
        basketResponse.setTotalPrice(basket.getTotalPrice());
        return basketResponse;
    }
}
