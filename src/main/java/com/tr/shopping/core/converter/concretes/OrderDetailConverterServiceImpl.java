package com.tr.shopping.core.converter.concretes;

import com.tr.shopping.core.converter.abstracts.OrderDetailConverterService;
import com.tr.shopping.core.model.response.OrderDetailResponse;
import com.tr.shopping.entity.OrderDetail;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailConverterServiceImpl implements OrderDetailConverterService {
    @Override
    public OrderDetailResponse orderDetailToOrderDetailResponse(OrderDetail orderDetail) {
        OrderDetailResponse orderDetailResponse=new OrderDetailResponse();
        orderDetailResponse.setOrderStatus(orderDetail.getOrderStatus().getName());
        orderDetailResponse.setCreationDate(orderDetail.getCreationDate());
        orderDetailResponse.setShipMethod(orderDetail.getShipMethod().getName());
        orderDetailResponse.setTotalAmount(orderDetail.getTotalAmount());
        return orderDetailResponse;
    }
}
