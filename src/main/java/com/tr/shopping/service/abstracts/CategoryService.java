package com.tr.shopping.service.abstracts;

import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.model.dto.CategoryDto;


public interface CategoryService {
    GeneralResponse createCategory(CategoryDto categoryDto);

}
