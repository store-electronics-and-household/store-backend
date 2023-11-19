package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.mapper.OrderMapper;
import ru.acceleration.store.model.dto.OrderRequest;
import ru.acceleration.store.model.dto.OrderResponse;
import ru.acceleration.store.service.OrderService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/order")
@Validated
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        log.info("Controller layer: POST /order request for user with id: {} obtained.", orderRequest.getUserId());

        return orderMapper.orderToOrderResponse(orderService.createOrder(orderMapper
                .orderRequestToOrder(orderRequest)));
    }

    @DeleteMapping("/{orderId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        log.info("Controller layer: DELETE /order/{orderId}/delete request for order with id: {} obtained.", orderId);

        orderService.deleteOrderById(orderId);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrderById(@PathVariable Long orderId) {
        log.info("Controller layer: GET /order/{orderId} request for order with id: {} obtained.", orderId);

        return orderMapper.orderToOrderResponse(orderService.getOrderById(orderId));
    }
}