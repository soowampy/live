package com.live.controller;

import com.live.controller.dto.UserCreateRequest;
import com.live.controller.dto.UserCreateResponse;
import com.live.service.UserCommandService;
import com.live.service.dto.UserCreatedDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserCommandService userCommandService;

    @PostMapping("/users")
    public ResponseEntity<UserCreateResponse> signUp(@RequestBody @Valid UserCreateRequest request) {
        UserCreatedDto userCreatedDto = userCommandService.signUp(request.toUserCreateDto());
        UserCreateResponse userCreateResponse = new UserCreateResponse(userCreatedDto.id(),
                userCreatedDto.email(),
                userCreatedDto.name(),
                userCreatedDto.createdAt());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userCreateResponse);
    }
}
