package com.tr.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tr.shopping.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category getCategoryByName(String name);

}
