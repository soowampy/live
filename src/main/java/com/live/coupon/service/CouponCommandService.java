package com.live.coupon.service;

import com.live.common.exception.CouponDuplicatedException;
import com.live.common.exception.CouponExpireException;
import com.live.common.exception.CouponNotFoundException;
import com.live.common.exception.DuplicateEmailException;
import com.live.coupon.domain.*;
import com.live.coupon.service.dto.CouponIssueDto;
import com.live.user.domain.User;
import com.live.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.live.common.exception.BusinessErrorMessage.*;

@Service
@RequiredArgsConstructor
public class CouponCommandService {

    private final CouponRepository couponRepository;
    private final CouponUserRepository couponUserRepository;
    private final UserQueryService userQueryService;

    @Transactional
    public void issue() {
        couponRepository.save(new Coupon(new CouponCode(), LocalDateTime.MAX));
    }

    @Transactional
    public void userIssue(CouponIssueDto couponIssue) {
        // 1. 만료일 지났는지 확인
        Coupon coupon = findByCouponCode(couponIssue.couponCode());
        if (coupon.isExpired()) throw new CouponExpireException(COUPON_EXPIRE.getMessage());

        try {
            User user =  userQueryService.getUser(couponIssue.userId());
            CouponUser couponUser = new CouponUser(user, coupon);
            couponUserRepository.save(couponUser);
        } catch (DataIntegrityViolationException e) {
        // 동시성 방지
            throw new CouponDuplicatedException(COUPON_DUPLICATED.getMessage());
        }
    }

    private Coupon findByCouponCode(CouponCode couponCode) {
        return couponRepository.findByCouponCode(couponCode)
                .orElseThrow(() -> new CouponNotFoundException(COUPON_NOT_FOUND.getMessage()));
    }

}
