package com.tr.shopping.service.concretes;

import com.tr.shopping.entity.Basket;
import com.tr.shopping.entity.OrderItem;
import com.tr.shopping.repository.OrderItemRepository;
import com.tr.shopping.service.abstracts.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
