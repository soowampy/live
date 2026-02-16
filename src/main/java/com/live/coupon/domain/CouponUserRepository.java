package com.live.coupon.domain;

import com.live.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponUserRepository extends JpaRepository<CouponUser, Long> {
}
