package com.tr.shopping.core.converter.concretes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tr.shopping.core.converter.abstracts.ProductConverterService;
import com.tr.shopping.entity.Product;
import com.tr.shopping.core.model.dto.ProductDto;
import com.tr.shopping.core.model.response.BrandResponse;
import com.tr.shopping.core.model.response.CategoryResponse;
import com.tr.shopping.core.model.response.ProductInventoryResponse;
import com.tr.shopping.core.model.response.ProductResponse;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductConverterServiceImpl implements ProductConverterService {



    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        Product product=new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        product.setCategory(productDto.getCategory());
        product.setStock(productDto.getStock());
        UUID uuid=UUID.randomUUID();
        product.setBarcode(uuid);
        return product;
    }

    @Override
    public ProductDto productToProductDto(Product product) {
        ProductDto productDto=new ProductDto();
        productDto.setCategory(product.getCategory());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setName(product.getName());
        productDto.setBrand(product.getBrand());
        return productDto;
    }

    @Override
    public ProductResponse productToProductResponse(Product product) {
        ProductResponse productResponse=new ProductResponse();
        BrandResponse brandResponse=new BrandResponse();
        brandResponse.setName(product.getBrand().getName());
        productResponse.setBrand(brandResponse);
        CategoryResponse categoryResponse=new CategoryResponse();
        categoryResponse.setParent(product.getCategory().getParent());
        categoryResponse.setName(product.getCategory().getName());
        productResponse.setCategory(categoryResponse);
        productResponse.setImage(product.getImage());
        productResponse.setPrice(product.getPrice());
        productResponse.setName(product.getName());
        ProductInventoryResponse stockRespose=new ProductInventoryResponse();
        stockRespose.setQuantity(product.getStock().getQuantity());
        productResponse.setStock(stockRespose);
        return productResponse;
    }


}
