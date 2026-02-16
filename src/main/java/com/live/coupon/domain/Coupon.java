package com.live.coupon.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "coupon")
public class Coupon {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Embedded
    private CouponCode couponCode;

    @Column(name ="created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "valid_at", nullable = false)
    private LocalDateTime validAt;

    public boolean isExpired() {
        return validAt.isBefore(LocalDateTime.now());
    }

    public Coupon(CouponCode couponCode, LocalDateTime validAt) {
        this(null, couponCode, null, validAt);
    }

    @PrePersist
    void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
