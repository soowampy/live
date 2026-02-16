package com.live.coupon.controller;

import com.live.coupon.controller.dto.CouponIssueRequest;
import com.live.coupon.domain.CouponCode;
import com.live.coupon.service.CouponCommandService;
import com.live.coupon.service.dto.CouponIssueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponController {
    private final CouponCommandService couponCommandService;

    @PostMapping("/issue")
    public ResponseEntity<Void> issue() {
        couponCommandService.issue();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(null);
    }

    @PostMapping("/user-issue")
    public ResponseEntity<Void> userIssue(@RequestBody CouponIssueRequest couponIssueRequest) {
        couponCommandService.userIssue(new CouponIssueDto(
                new CouponCode(couponIssueRequest.couponCode()),
                couponIssueRequest.userId()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(null);
    }
}
