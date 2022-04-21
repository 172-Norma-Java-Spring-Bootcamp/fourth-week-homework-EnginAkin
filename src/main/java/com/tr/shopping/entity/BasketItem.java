package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class BasketItem  extends BaseModel {

    @Column(nullable = false)
    private BigDecimal quantity=BigDecimal.ZERO;
    @Column(nullable = false)
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal discountPrice = BigDecimal.ZERO;
    private BigDecimal taxPrice = BigDecimal.ZERO;
    private BigDecimal shippingPrice = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    private Basket basket;

    @ManyToOne
    private Product product;

    public void calculateBasketItemPrice() {
        this.price=product.getPrice().multiply(this.quantity);
    }
}
