package com.live.order.controller;

import com.live.order.controller.dto.OrderCreateRequest;
import com.live.order.service.OrderCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderCommandService orderCommandService;

    @PostMapping("/orders")
    public ResponseEntity<Long> order(@RequestBody OrderCreateRequest request) {
        Long orderId = orderCommandService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderId);
    }
}
