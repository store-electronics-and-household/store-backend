package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.mapper.DeliveryMapper;
import ru.acceleration.store.model.dto.DeliveryRequest;
import ru.acceleration.store.model.dto.DeliveryResponse;
import ru.acceleration.store.service.DeliveryService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/delivery")
@Validated
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryResponse createDelivery(@Valid @RequestBody DeliveryRequest deliveryRequest) {
        log.info("Controller layer: POST /delivery request for user with id: {} obtained.",
                deliveryRequest.getUserId());

        return deliveryMapper.deliveryToDeliveryResponse(deliveryService.createDelivery(deliveryMapper
                .deliveryRequestToDelivery(deliveryRequest)));
    }

    @DeleteMapping("/{deliveryId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long deliveryId) {
        log.info("Controller layer: DELETE /delivery/{deliveryId}/delete request for delivery with id: {} obtained.",
                deliveryId);

        deliveryService.deleteDeliveryById(deliveryId);
    }

    @GetMapping("/{deliveryId}")
    public DeliveryResponse getDeliveryById(@PathVariable Long deliveryId) {
        log.info("Controller layer: GET /delivery/{deliveryId} request for delivery with id: {} obtained.", deliveryId);

        return deliveryMapper.deliveryToDeliveryResponse(deliveryService.getDeliveryById(deliveryId));
    }
}
