package com.tr.shopping.core.model.dto;

import lombok.Getter;
import lombok.Setter;
import com.tr.shopping.entity.Brand;
import com.tr.shopping.entity.Category;
import com.tr.shopping.entity.ProductInventory;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    private String image;
    @NotNull
    private Brand brand;
    @NotNull
    private Category category;
    @NotNull
    private ProductInventory stock;
}
