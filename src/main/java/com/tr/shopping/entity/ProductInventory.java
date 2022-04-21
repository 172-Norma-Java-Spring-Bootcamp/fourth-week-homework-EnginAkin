package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ProductInventory extends BaseModel {
    private BigDecimal quantity=BigDecimal.ZERO;
}
