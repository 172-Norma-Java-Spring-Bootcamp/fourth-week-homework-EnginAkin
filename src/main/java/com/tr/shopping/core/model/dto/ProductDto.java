package com.tr.shopping.core.model.dto;

import lombok.Getter;
import lombok.Setter;
import com.tr.shopping.entity.Brand;
import com.tr.shopping.entity.Category;
import com.tr.shopping.entity.ProductInventory;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private String name;
    private BigDecimal price;
    private String image;
    private Brand brand;
    private Category category;
    private ProductInventory stock;
}
