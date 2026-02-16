package com.live.user.controller.dto;

import com.live.user.domain.Email;
import com.live.user.domain.Name;
import com.live.user.service.dto.UserCreateDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest (
        @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 미만")
        @NotBlank(message = "이름은 필수 입력값")
        String name,
        @NotBlank(message = "이메일은 필수 입력값")
        @jakarta.validation.constraints.Email(message = "이메일 형식이 유효하지 않습니다.")
        String email
){
        public UserCreateDto toUserCreateDto() {
               return new UserCreateDto(new Email(email), new Name(name));
        }
}
