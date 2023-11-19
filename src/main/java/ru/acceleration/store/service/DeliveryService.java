package ru.acceleration.store.service;

import ru.acceleration.store.model.Delivery;

public interface DeliveryService {
    Delivery createDelivery(Delivery delivery);

    void deleteDeliveryById(Long deliveryId);

    Delivery getDeliveryById(Long DeliveryId);
}