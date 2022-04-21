package com.tr.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tr.shopping.entity.Customer;
import com.tr.shopping.entity.CustomerAddress;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Long> {


    CustomerAddress getCustomerAddressById(Long id);
    Customer getCustomerById(Long id);
    Boolean existsCustomerByUsername(String username);


    @Query(value = "select * from Customer c where c.is_deleted!=TRUE",nativeQuery = true)
    List<Customer> getCustomerUnDeleted();
}
