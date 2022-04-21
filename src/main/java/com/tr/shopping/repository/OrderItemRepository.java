package com.tr.shopping.repository;

import com.tr.shopping.entity.Category;
import com.tr.shopping.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {


    boolean existsOrderItemBy();

}
