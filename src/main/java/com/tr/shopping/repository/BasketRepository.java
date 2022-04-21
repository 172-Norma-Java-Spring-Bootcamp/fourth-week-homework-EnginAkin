package com.tr.shopping.repository;

import com.tr.shopping.entity.Basket;
import com.tr.shopping.entity.Customer;
import com.tr.shopping.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {

}
