package ru.acceleration.store.service.product;

import ru.acceleration.store.dto.product.NewProductDto;
import ru.acceleration.store.dto.product.ProductFullDto;
import ru.acceleration.store.dto.product.ProductShortDto;
import ru.acceleration.store.model.Product;

import java.util.List;

public interface ProductService {

    ProductShortDto addProduct(NewProductDto newProductDto);

    List<ProductShortDto> productsInPromotion(Long promotionId);

    ProductFullDto getProductById(Long productId);

    Product getExistingProduct(Long productId);
}
