package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.Order;
import ru.acceleration.store.repository.OrderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Order createOrder(Order order) {
        log.info("Service layer: POST /order request for user with id: {} obtained.", order.getUser().getId());

        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public void deleteOrderById(Long orderId) {
        log.info("Service layer: DELETE /order/{orderId}/delete request for order with id: {} obtained.", orderId);

        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Order with id: " + orderId + " doesn't exist in database.");
        }
    }

    @Override
    public Order getOrderById(Long orderId) {
        log.info("Service layer: GET /order/{orderId} request for order with id: {} obtained.", orderId);

        return orderRepository.findById(orderId).orElseThrow(() ->
                new DataNotFoundException("Order with id: " + orderId + " doesn't exist in database."));
    }
}