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
public class User {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Email email;

    @Enumerated(EnumType.STRING)
    private Name name;

    @CreatedDate
    @Column(name ="created_at", nullable = false)
    private LocalDateTime createdAt;

    public User(Email email, Name name) {
        this(null, email, name, null);
    }
}

