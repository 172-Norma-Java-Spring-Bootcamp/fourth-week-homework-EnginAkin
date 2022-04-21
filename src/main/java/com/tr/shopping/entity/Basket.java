package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Basket extends BaseModel {

    @Column(nullable = false)
    private BigDecimal price=BigDecimal.ZERO;

    private BigDecimal discountPrice=BigDecimal.ZERO;
    private BigDecimal taxPrice=BigDecimal.ZERO;
    private BigDecimal shippingPrice=BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal totalPrice=BigDecimal.ZERO;

    @OneToMany(mappedBy = "basket",cascade = CascadeType.ALL)
    private Set<BasketItem> items = new HashSet<>();

    public void addBasketItemToBasket(BasketItem basketItem){
        items.add(basketItem);
        calculateBasket(basketItem);
    }
    private void calculateBasket(BasketItem basketItem){
            this.price=price.add(basketItem.getPrice());
            this.discountPrice=discountPrice.add(basketItem.getDiscountPrice());
            this.taxPrice=taxPrice.add(basketItem.getTaxPrice());
            this.shippingPrice=shippingPrice.add(basketItem.getShippingPrice());
            this.totalPrice=price.subtract(discountPrice).add(taxPrice).add(shippingPrice);
    }

}
