package ru.acceleration.store.service;

import ru.acceleration.store.dto.NewProductDto;
import ru.acceleration.store.dto.ProductFullDto;
import ru.acceleration.store.dto.ProductShortDto;
import ru.acceleration.store.model.Product;

import java.util.List;

public interface ProductService {

    ProductShortDto addProduct(NewProductDto newProductDto);

    List<ProductShortDto> productsInPromotion(Long promotionId);

    ProductFullDto getProductById(Long productId);

    Product getExistingProduct(Long productId);
}
