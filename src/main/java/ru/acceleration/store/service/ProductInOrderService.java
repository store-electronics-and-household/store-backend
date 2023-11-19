package ru.acceleration.store.service;

import ru.acceleration.store.model.ProductInOrder;

public interface ProductInOrderService {
    ProductInOrder createProductInOrder(ProductInOrder productInOrder);

    void deleteProductInOrderById(Long productInOrderId);

    ProductInOrder getProductInOrderById(Long productInOrderId);
}
