package com.tr.shopping.service.abstracts;

import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.model.dto.CategoryDto;
import com.tr.shopping.entity.Category;


public interface CategoryService {
    GeneralResponse createCategory(CategoryDto categoryDto);
    Category getCategoryByName(String name);

}
