package ru.acceleration.store.service;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.AttributeCategoryRequest;
import ru.acceleration.store.dto.AttributeProductRequest;
import ru.acceleration.store.dto.AttributeResponse;

@Service
public interface AttributeService {

    AttributeResponse getAttributeCategory(AttributeCategoryRequest dto);

    AttributeResponse getAttributeProduct(AttributeProductRequest dto);
}
