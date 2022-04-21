package com.tr.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tr.shopping.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query(value = "select * from  product p where p.name= :categoryName",nativeQuery = true)
    List<Product> getProductsByCategoryName(String categoryName);

}
