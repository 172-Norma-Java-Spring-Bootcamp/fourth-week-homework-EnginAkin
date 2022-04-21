package com.tr.shopping.core.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {
    private String name;
    private BigDecimal price;
    private String image;
    private BrandResponse brand;
    private CategoryResponse category;
    private ProductInventoryResponse stock;

    // barkod kendimiz tarafından oluşturulacaktır.
}
