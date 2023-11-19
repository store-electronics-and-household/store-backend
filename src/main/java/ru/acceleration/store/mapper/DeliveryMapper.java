package ru.acceleration.store.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.acceleration.store.model.Delivery;
import ru.acceleration.store.model.dto.DeliveryRequest;
import ru.acceleration.store.model.dto.DeliveryResponse;
import ru.acceleration.store.service.OrderService;
import ru.acceleration.store.service.UserService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DeliveryMapper {
    private final OrderService orderService;
    private final UserService userService;

    public Delivery deliveryRequestToDelivery(DeliveryRequest deliveryRequest) {
        Delivery delivery = new Delivery();
        delivery.setOrder(orderService.getOrderById(deliveryRequest.getOrderId()));
        delivery.setUser(userService.getUserName(deliveryRequest.getUserId()));
        delivery.setStatus(deliveryRequest.getStatus());
        delivery.setCreated(LocalDateTime.now());

        return delivery;
    }

    public DeliveryResponse deliveryToDeliveryResponse(Delivery delivery) {
        DeliveryResponse deliveryResponse = new DeliveryResponse();
        deliveryResponse.setDeliveryId(delivery.getId());
        deliveryResponse.setOrderId(delivery.getOrder().getId());
        deliveryResponse.setStatus(delivery.getStatus());
        deliveryResponse.setCreated(delivery.getCreated());

        return deliveryResponse;
    }
}