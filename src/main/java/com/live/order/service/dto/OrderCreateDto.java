package com.live.order.service.dto;

import com.live.order.controller.dto.OrderCreateRequest;
import com.live.order.domain.Product;
import com.live.user.domain.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderCreateDto (
        @NotNull User user,
        @NotEmpty List<OrderCreateRequest.OrderItemRequest> items
) {
    public record OrderItemDto(
            @NotNull Product product,
            @Min(1) int quantity
    ) {}
}