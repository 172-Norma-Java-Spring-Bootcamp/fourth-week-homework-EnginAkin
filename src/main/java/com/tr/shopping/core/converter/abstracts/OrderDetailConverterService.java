package com.tr.shopping.core.converter.abstracts;

import com.tr.shopping.core.model.response.OrderDetailResponse;
import com.tr.shopping.entity.OrderDetail;

public interface OrderDetailConverterService {
    OrderDetailResponse orderDetailToOrderDetailResponse(OrderDetail orderDetail);
}
