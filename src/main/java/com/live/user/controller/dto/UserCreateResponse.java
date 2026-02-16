package com.live.user.controller.dto;

import java.time.LocalDateTime;

public record UserCreateResponse(
        Long id,
        String email,
        String name,
        LocalDateTime createdAt
) {
}
