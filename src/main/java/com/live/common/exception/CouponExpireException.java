package com.live.common.exception;

import com.live.coupon.domain.CouponCode;

public class CouponExpireException extends RuntimeException {
    public CouponExpireException(String message) {super(message);}
}
