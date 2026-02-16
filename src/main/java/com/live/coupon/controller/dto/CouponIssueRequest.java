package com.live.coupon.controller.dto;

public record CouponIssueRequest (
        String couponCode,
        Long userId
){
}
