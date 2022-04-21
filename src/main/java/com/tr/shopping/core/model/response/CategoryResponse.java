package com.tr.shopping.core.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import com.tr.shopping.entity.Category;

@Getter
@Setter
public class CategoryResponse {

    private String name;
    @JsonIgnore
    private Category parent;
}
