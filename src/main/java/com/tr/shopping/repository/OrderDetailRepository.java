package com.tr.shopping.repository;

import com.tr.shopping.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    Boolean existsOrderDetailByCustomerId(Long customerId);

    OrderDetail getOrderDetailByCustomerId(long customerId);
}
