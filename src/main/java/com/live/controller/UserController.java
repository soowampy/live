package com.live.controller;

import com.live.controller.dto.UserCreateRequest;
import com.live.controller.dto.UserCreateResponse;
import com.live.controller.dto.UserResponse;
import com.live.domain.User;
import com.live.service.UserCommandService;
import com.live.service.UserQueryService;
import com.live.service.dto.UserCreatedDto;
import com.live.service.dto.UserDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

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

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        UserDto user = userQueryService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UserResponse(
                        user.id(),
                        user.email(),
                        user.name(),
                        user.createdAt()
                ));
    }
}
