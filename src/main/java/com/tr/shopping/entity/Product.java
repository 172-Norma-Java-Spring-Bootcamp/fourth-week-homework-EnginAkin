package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Product extends BaseExtendedModel {
    private String name;
    private BigDecimal price;
    private UUID barcode;
    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductInventory stock;

    @OneToOne(cascade = CascadeType.ALL)
    private Brand brand;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;






}
