package com.live.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "ux_users_email", columnNames = "email")
        }
)
public class User {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Name name;

    @CreatedDate
    @Column(name ="created_at", nullable = false)
    private LocalDateTime createdAt;

    public User(Email email, Name name) {
        this(null, email, name, null);
    }

    @PrePersist
    void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}

