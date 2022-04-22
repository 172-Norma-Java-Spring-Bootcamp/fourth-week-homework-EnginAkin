package com.tr.shopping.repository;

import com.tr.shopping.entity.CustomerCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCouponRepository extends JpaRepository<CustomerCoupon,Long> {

}
