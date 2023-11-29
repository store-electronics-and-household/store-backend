package ru.acceleration.store.service.attribute;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.attribute.AttributeCategoryRequest;
import ru.acceleration.store.dto.attribute.AttributeCategoryResponse;
import ru.acceleration.store.dto.attribute.AttributeProductRequest;
import ru.acceleration.store.dto.attribute.AttributeProductResponse;

@Service
public interface AttributeService {

    AttributeCategoryResponse getAttributeCategory(AttributeCategoryRequest dto);

    AttributeProductResponse getAttributeProduct(AttributeProductRequest dto);
}
