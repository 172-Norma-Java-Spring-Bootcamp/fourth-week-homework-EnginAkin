package com.tr.shopping.core.converter.concretes;

import org.springframework.stereotype.Service;
import com.tr.shopping.core.converter.abstracts.CategoryConverterService;
import com.tr.shopping.entity.Category;
import com.tr.shopping.core.model.dto.CategoryDto;

@Service
public class CategoryConverterServiceImpl implements CategoryConverterService {
    @Override
    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        Category category=new Category();
        category.setParent(categoryDto.getParent());
        category.setName(categoryDto.getName());
        return category;
    }

    @Override
    public CategoryDto categoryToCategoryDto(Category category) {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setParent(category.getParent());
        categoryDto.setName(categoryDto.getName());
        return categoryDto;
    }
}
