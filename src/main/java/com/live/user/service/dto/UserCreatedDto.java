package com.live.user.service.dto;

import java.time.LocalDateTime;

public record UserCreatedDto (
        Long id,
        String email,
        String name,
        LocalDateTime createdAt
){
}
