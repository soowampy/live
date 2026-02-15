package com.live.controller;

import com.live.service.UserCommandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserApiController {
    private final UserCommandService userCommandService;
}
