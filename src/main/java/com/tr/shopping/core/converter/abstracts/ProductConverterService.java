package com.tr.shopping.core.converter.abstracts;

import com.tr.shopping.entity.Product;
import com.tr.shopping.core.model.dto.ProductDto;
import com.tr.shopping.core.model.response.ProductResponse;

public interface ProductConverterService {


    Product productDtoToProduct(ProductDto productDto);

    ProductDto productToProductDto(Product product);

    ProductResponse productToProductResponse(Product product);
}
