package com.tr.shopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.tr.shopping.core.exception.AddingProductNonExistCategoryException;
import com.tr.shopping.core.response.GeneralResponse;
import com.tr.shopping.core.model.dto.ProductDto;
import com.tr.shopping.service.abstracts.ProductService;

@RestController
@RequestMapping(path = "api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public GeneralResponse createProduct(@RequestBody ProductDto productDto)throws AddingProductNonExistCategoryException {
        return productService.createProduct(productDto);
    }
    @DeleteMapping(path = "/{productId}")
    public GeneralResponse deleteProduct(@PathVariable("productId") int id){
        return productService.deleteProductById((long)id);
    }
    @GetMapping(path = "/{productId}")
    public GeneralResponse getProductById(@PathVariable("productId") int id){
        return productService.getProductById((long)id);
    }
    @GetMapping
    public GeneralResponse getProducts(){
        return productService.getAllProducts();
    }





}
