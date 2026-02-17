package com.live.order.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderCreateRequest(
        @NotNull Long userId,
        @NotEmpty List<OrderItemRequest> items
) {
    public record OrderItemRequest(
            @NotNull Long productId,
            @Min(1) int quantity
    ) {}
}