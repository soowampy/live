package com.live.user.service.dto;

import com.live.user.domain.Email;
import com.live.user.domain.Name;

public record UserCreateDto (
        Email email,
        Name name
){
}
