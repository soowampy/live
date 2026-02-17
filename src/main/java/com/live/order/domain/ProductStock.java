package com.live.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Getter
@EqualsAndHashCode
@Embeddable
public class ProductStock {
    @Column(name = "stock")
    private int value;

    public ProductStock(int value) {
        this.value = value;
    }

    protected ProductStock() {
        this.value = 0;
    }

    public void increase(int qty) {
        this.value = this.value + qty;
    }

    public void decrease(int qty) {
        if (qty <= this.value) {
            this.value = this.value - qty;
        } else {
            this.value = 0;
        }
    }
}
