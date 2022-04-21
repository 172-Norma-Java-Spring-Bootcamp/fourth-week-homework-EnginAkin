package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Brand extends BaseModel {
    private String name;

}
