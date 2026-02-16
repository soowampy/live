package com.live.coupon.service.dto;

import com.live.coupon.domain.CouponCode;

public record CouponIssueDto (
        CouponCode couponCode,
        Long userId
){
}
