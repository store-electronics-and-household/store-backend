package ru.acceleration.store.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.attribute.AttributeCategoryRequest;
import ru.acceleration.store.dto.attribute.AttributeCategoryResponse;
import ru.acceleration.store.dto.attribute.AttributeProductRequest;
import ru.acceleration.store.dto.attribute.AttributeProductResponse;
import ru.acceleration.store.service.attribute.AttributeService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/attribute")
@Slf4j
public class AttributeController {

    private final AttributeService attributeService;

    @Operation(summary = "Получение аттрибутов категории", description = "Доступ для всех")
    @GetMapping("/category")
    public AttributeCategoryResponse getAttributeCategory(@RequestBody @Valid AttributeCategoryRequest dto) {
        log.info("AttributeController postUser dto {}", dto);
        AttributeCategoryResponse attributeCategoryResponse = attributeService.getAttributeCategory(dto);
        return attributeCategoryResponse;
    }

    @Operation(summary = "Получение аттрибутов модели", description = "Доступ для всех")
    @GetMapping("/product")
    public AttributeProductResponse getAttributeProduct(@RequestBody @Valid AttributeProductRequest dto) {
        log.info("AttributeController getAttributeProduct dto {}", dto);
        return attributeService.getAttributeProduct(dto);
    }
}
