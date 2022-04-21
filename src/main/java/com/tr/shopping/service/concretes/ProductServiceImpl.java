package com.tr.shopping.service.concretes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.tr.shopping.core.converter.concretes.ConverterService;
import com.tr.shopping.core.constant.ProductResponseMessage;
import com.tr.shopping.core.exception.AddingProductNonExistCategoryException;
import com.tr.shopping.core.exception.CustomerIdCannotFountException;
import com.tr.shopping.core.exception.ProductIdCannotFoundException;
import com.tr.shopping.core.response.GeneralDataResponse;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.response.GeneralSuccessfullResponse;
import com.tr.shopping.entity.Category;
import com.tr.shopping.entity.Product;
import com.tr.shopping.core.model.dto.ProductDto;
import com.tr.shopping.core.model.response.ProductResponse;
import com.tr.shopping.repository.CategoryRepository;
import com.tr.shopping.repository.ProductRepository;
import com.tr.shopping.service.abstracts.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ConverterService converterService;
    private final CategoryRepository categoryRepository;

    @Override
    public GeneralResponse getProductById(Long id) {
        if(!productRepository.findById(id).isPresent()) throw new ProductIdCannotFoundException();
        Product product = productRepository.findById(id).get();

        ProductResponse productResponse=converterService.getProductConverterService().productToProductResponse(product);
        return new GeneralDataResponse<>(ProductResponseMessage.PRODUCT_GET_BYID_SUCCESSFUL,true,productResponse);
    }

    @Override
    public GeneralResponse createProduct(ProductDto productDto)throws AddingProductNonExistCategoryException {
        checkCategoryIsAcceptable(productDto); // check category is already exist , if not exist category throw error , if exist category add product
        Product product = converterService.getProductConverterService().productDtoToProduct(productDto);//
        productRepository.save(product);
        return new GeneralSuccessfullResponse(ProductResponseMessage.PRODUCT_CREATED_SUCCESSFULL);
    }


    @Override
    public GeneralResponse getProductsByCategoryName(String categoryName) {
        return new GeneralDataResponse<>(productRepository.getProductsByCategoryName(categoryName));
    }

    @Override
    public GeneralResponse deleteProductById(Long id) {
        if(productRepository.existsById(id)) throw new CustomerIdCannotFountException();
        productRepository.deleteById(id);
        return new GeneralSuccessfullResponse(ProductResponseMessage.PRODUCT_DELETED_SUCCESSFULL);
    }
    @Override
    public GeneralResponse getAllProducts() { // get all products . returned product if  stock > 0
         List<ProductResponse> products=productRepository.findAll().stream()
                .filter(product -> product.getStock().getQuantity().compareTo(BigDecimal.ZERO)>0)
                .map(product -> converterService.getProductConverterService().productToProductResponse(product)).collect(Collectors.toList());
        return new GeneralDataResponse<>("all product returned ", true,products);
    }

    private void checkCategoryIsAcceptable(ProductDto productDto){
        if(isAlreadyExistCategory(productDto.getCategory())){
            Category findcategoryInParent = FindParentCategory(productDto.getCategory());
            productDto.setCategory(findcategoryInParent);
        }else{
            throw new AddingProductNonExistCategoryException();
        }
    }
    private Boolean isAlreadyExistCategory(Category category){
        if(Objects.isNull(category)) return true;
        Category categoryByName = categoryRepository.getCategoryByName(category.getName());
        if(categoryByName==null) {
            return false;
        }
        return isAlreadyExistCategory(category.getParent());

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
