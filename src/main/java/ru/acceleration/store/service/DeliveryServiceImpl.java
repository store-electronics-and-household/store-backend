package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.Delivery;
import ru.acceleration.store.repository.DeliveryRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Transactional
    @Override
    public Delivery createDelivery(Delivery delivery) {
        log.info("Service layer: POST /delivery request for user with id: {} obtained.", delivery.getUser().getId());

        return deliveryRepository.save(delivery);
    }

    @Transactional
    @Override
    public void deleteDeliveryById(Long deliveryId) {
        log.info("Service layer: DELETE /delivery/{deliveryId}/delete request for delivery with id: {} obtained.",
                deliveryId);

        try {
            deliveryRepository.deleteById(deliveryId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Delivery with id: " + deliveryId + " doesn't exist in database.");
        }
    }

    @Override
    public Delivery getDeliveryById(Long deliveryId) {
        log.info("Service layer: GET /delivery/{deliveryId} request for delivery with id: {} obtained.", deliveryId);

        return deliveryRepository.findById(deliveryId).orElseThrow(() ->
                new DataNotFoundException("Delivery with id: " + deliveryId + " doesn't exist in database."));
    }
}