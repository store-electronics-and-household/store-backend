package ru.acceleration.store.service;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.AttributeCategoryRequest;
import ru.acceleration.store.dto.AttributeCategoryResponse;
import ru.acceleration.store.dto.AttributeProductRequest;
import ru.acceleration.store.dto.AttributeProductResponse;

@Service
public interface AttributeService {

    AttributeCategoryResponse getAttributeCategory(AttributeCategoryRequest dto);

    AttributeProductResponse getAttributeProduct(AttributeProductRequest dto);
}
