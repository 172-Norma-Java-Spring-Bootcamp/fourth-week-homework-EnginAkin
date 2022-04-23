package com.tr.shopping.service.concretes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.tr.shopping.core.converter.concretes.ConverterService;
import com.tr.shopping.core.constant.CategoryResponseMessage;
import com.tr.shopping.core.response.GeneralErrorResponse;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.response.GeneralSuccessfullResponse;
import com.tr.shopping.entity.Category;
import com.tr.shopping.core.model.dto.CategoryDto;
import com.tr.shopping.repository.CategoryRepository;
import com.tr.shopping.service.abstracts.CategoryService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ConverterService converterService;

    @Override
    public GeneralResponse createCategory(CategoryDto categoryDto) {
        Category category= converterService.getCategoryConverterService().categoryDtoToCategory(categoryDto);
        if(categoryRepository.getCategoryByName(category.getName()) == null){
            Category categoryByName= FindParentCategory(category);
            log.info("Category saved successfull : {}",categoryDto.getName());
            if(categoryByName==null){
                categoryRepository.save(category);
                return new GeneralSuccessfullResponse(CategoryResponseMessage.CATEGORY_CREATE_CATEGORY_SUCCESSFULL);
            }
            category.setParent(categoryByName);
            categoryRepository.save(category);
            return new GeneralSuccessfullResponse(CategoryResponseMessage.CATEGORY_CREATE_CATEGORY_SUCCESSFULL);
        }
        log.info("Error : Category already have in database : {}",categoryDto.getName());
        return new GeneralErrorResponse(CategoryResponseMessage.CATEGORY_CREATE_CATEGORY_UNSUCCESSFULL);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }


    private Category FindParentCategory(Category category){
        if(Objects.isNull(category)) return null;
        Category categoryByName = categoryRepository.getCategoryByName(category.getName());
        if(categoryByName!=null) {
            return categoryByName;
        }
        return FindParentCategory(category.getParent());

    }
}
