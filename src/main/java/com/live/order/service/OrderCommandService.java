package com.live.order.service;

import com.live.common.exception.OutOfStockException;
import com.live.common.exception.ProductNotFoundException;
import com.live.order.controller.dto.OrderCreateRequest;
import com.live.order.domain.Order;
import com.live.order.domain.OrderRepository;
import com.live.order.domain.Product;
import com.live.order.domain.ProductRepository;
import com.live.order.service.dto.OrderCreateDto;
import com.live.user.domain.User;
import com.live.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.live.common.exception.BusinessErrorMessage.OUT_OF_STOCK;
import static com.live.common.exception.BusinessErrorMessage.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderCommandService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserQueryService userQueryService;

    @Transactional
    public Long createOrder(OrderCreateRequest req) {
        // 상품 존재 체크
        List<Long> productIds = req.items().stream()
                .map(OrderCreateRequest.OrderItemRequest::productId)
                .toList();
        List<Product> products = productRepository.findAllById(productIds);
        if (products.size() != productIds.stream().distinct().count()) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND.getMessage());
        }

        // 재고 차감
        for (var item : req.items()) {
            int updated = productRepository.decreaseStockIfEnough(item.productId(), item.quantity());
            if (updated == 0) {
                throw new OutOfStockException(OUT_OF_STOCK.getMessage());
            }
        }

        // 주문 저장
        User user = userQueryService.getUser(req.userId());
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));
        List<OrderCreateDto.OrderItemDto> orderItems = req.items().stream()
                .map(item -> new OrderCreateDto.OrderItemDto(
                        productMap.get(item.productId()),
                        item.quantity()
                ))
                .toList();
        Order order = Order.create(user);
        for (var item : orderItems) {
            order.addItem(item.product(), item.quantity());
        }

        return orderRepository.save(order).getId();
    }
}
