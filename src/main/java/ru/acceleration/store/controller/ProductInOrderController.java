package ru.acceleration.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.mapper.ProductInOrderMapper;
import ru.acceleration.store.model.dto.ProductInOrderRequest;
import ru.acceleration.store.model.dto.ProductInOrderResponse;
import ru.acceleration.store.service.ProductInOrderService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product-in-order")
public class ProductInOrderController {
    private final ProductInOrderService productInOrderService;
    private final ProductInOrderMapper productInOrderMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductInOrderResponse createProductInOrder(@RequestBody ProductInOrderRequest productInOrderRequest) {
        log.info("Controller layer: POST /product-in-order request obtained.");

        return productInOrderMapper.productInOrderToProductInOrderResponse(productInOrderService
                .createProductInOrder(productInOrderMapper
                        .productInOrderRequestToProductInOrder(productInOrderRequest)));
    }

    @DeleteMapping("/{productInOrderId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductInOrder(@PathVariable Long productInOrderId) {
        log.info("Controller layer: DELETE /product-in-order/{productInOrderId}/delete request for product in order" +
                " with id: {} obtained.", productInOrderId);

        productInOrderService.deleteProductInOrderById(productInOrderId);
    }

    @GetMapping("/{productInOrderId}")
    public ProductInOrderResponse getProductInOrderById(@PathVariable Long productInOrderId) {
        log.info("Controller layer: GET /product-in-order/{productInOrderId} request for product in order with id: {}" +
                " obtained.", productInOrderId);

        return productInOrderMapper.productInOrderToProductInOrderResponse(productInOrderService
                .getProductInOrderById(productInOrderId));
    }
}