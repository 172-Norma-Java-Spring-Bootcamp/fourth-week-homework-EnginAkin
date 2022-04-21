package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Category  extends BaseModel {

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Category parent;

    @Override
    public String toString() {
        if(!Objects.isNull(parent)) return "-> name='" + name + '\''+parent;
        return " name ->"+name;
    }
}
