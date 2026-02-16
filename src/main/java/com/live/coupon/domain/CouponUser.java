package com.live.coupon.domain;

import com.live.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(
        name = "coupon_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "ux_coupon_user",
                        columnNames = {"user_id", "coupon_id"})
        })
public class CouponUser {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    private LocalDateTime issuedAt;

    public CouponUser(User user, Coupon coupon) {
        this.user = user;
        this.coupon = coupon;
        this.issuedAt = LocalDateTime.now();
    }
}
