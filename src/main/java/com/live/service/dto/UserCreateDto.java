package com.live.service.dto;

import com.live.domain.Email;
import com.live.domain.Name;

public record UserCreateDto (
        Email email,
        Name name
){
}
