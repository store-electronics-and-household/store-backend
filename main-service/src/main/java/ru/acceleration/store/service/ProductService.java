package ru.acceleration.store.service;

import ru.acceleration.store.dto.NewProductDto;
import ru.acceleration.store.dto.ProductShortDto;

import java.util.List;

public interface ProductService {

    ProductShortDto addProduct(NewProductDto newProductDto);

    List<ProductShortDto> productsInPromotion(Long promotionId);
}
