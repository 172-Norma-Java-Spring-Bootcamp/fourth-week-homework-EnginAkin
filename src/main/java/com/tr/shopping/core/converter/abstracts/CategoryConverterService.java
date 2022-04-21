package com.tr.shopping.core.converter.abstracts;

import com.tr.shopping.entity.Category;
import com.tr.shopping.core.model.dto.CategoryDto;

public interface CategoryConverterService {


    Category categoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto categoryToCategoryDto(Category category);

}
