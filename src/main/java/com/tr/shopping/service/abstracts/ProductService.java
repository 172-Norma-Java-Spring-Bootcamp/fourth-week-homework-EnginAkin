package com.tr.shopping.service.abstracts;

import com.tr.shopping.core.exception.AddingProductNonExistCategoryException;
import com.tr.shopping.core.response.GeneralDataResponse;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.model.dto.ProductDto;

public interface ProductService {

     GeneralDataResponse<ProductDto> getProductById(Long id);
    GeneralResponse createProduct(ProductDto productDto) throws AddingProductNonExistCategoryException;
    GeneralResponse getProductsByCategoryName(String categoryName);

    GeneralResponse deleteProductById(Long id);

    GeneralDataResponse getAllProducts();
}
