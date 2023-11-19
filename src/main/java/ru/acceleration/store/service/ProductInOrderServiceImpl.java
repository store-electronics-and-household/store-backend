package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.ProductInOrder;
import ru.acceleration.store.repository.ProductInOrderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductInOrderServiceImpl implements ProductInOrderService {
    private final ProductInOrderRepository productInOrderRepository;

    @Transactional
    @Override
    public ProductInOrder createProductInOrder(ProductInOrder productInOrder) {
        log.info("Service layer: POST /product-in-order request for product in order with id: {} obtained.",
                productInOrder.getId());

        return productInOrderRepository.save(productInOrder);
    }

    @Transactional
    @Override
    public void deleteProductInOrderById(Long productInOrderId) {
        log.info("Service layer: DELETE /product-in-order/{productInOrderId}/delete request for product in order with" +
                " id: {} obtained.", productInOrderId);

        try {
            productInOrderRepository.deleteById(productInOrderId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Product in order with id: " + productInOrderId +
                    " doesn't exist in database.");
        }
    }

    @Override
    public ProductInOrder getProductInOrderById(Long productInOrderId) {
        log.info("Service layer: GET /product-in-order/{productInOrderId} request for product in order with id: {}" +
                " obtained.", productInOrderId);

        return productInOrderRepository.findById(productInOrderId).orElseThrow(() ->
                new DataNotFoundException("Product in order with id: " + productInOrderId +
                        " doesn't exist in database."));
    }
}