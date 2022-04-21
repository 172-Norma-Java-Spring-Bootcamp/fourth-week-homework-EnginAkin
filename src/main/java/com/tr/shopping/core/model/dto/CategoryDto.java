package com.tr.shopping.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import com.tr.shopping.entity.Category;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CategoryDto {

    @NotNull
    private String name;
    private Category parent;
}
