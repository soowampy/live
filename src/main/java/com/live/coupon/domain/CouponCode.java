package com.live.coupon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@Embeddable
public class CouponCode {
    @Column(name = "coupon_code", nullable = false)
    private final String value;

    public CouponCode() {
        this.value = UUID.randomUUID().toString().replace("-", "");
    }

    public CouponCode(String couponCode) {
        this.value = couponCode;
    }
}
