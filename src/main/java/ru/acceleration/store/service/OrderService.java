package ru.acceleration.store.service;

import ru.acceleration.store.model.Order;

public interface OrderService {
    Order createOrder(Order order);

    void deleteOrderById(Long orderId);

    Order getOrderById(Long orderId);
}
