package ru.acceleration.store.service;

import ru.acceleration.store.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductByCategory(Long categoryId);

    List<Long> getProductIdByCategory(Long categoryId);

}
