package com.tr.shopping.repository;

import com.tr.shopping.entity.Customer;
import com.tr.shopping.entity.CustomerAddress;
import com.tr.shopping.entity.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment,Long> {


    Boolean existsCustomerPaymentByCustomerId(Long customerId);

    CustomerPayment getCustomerPaymentByCustomerId(Long customerId);
}
