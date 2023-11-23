package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.*;
import ru.acceleration.store.service.AttributeService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/attribute")
@Slf4j
public class AttributeController {

    private final AttributeService attributeService;

    @GetMapping("/category")
    public AttributeCategoryResponse getAttributeCategory(@RequestBody @Valid AttributeCategoryRequest dto) {
        log.info("AttributeController postUser dto {}", dto);
        AttributeCategoryResponse attributeCategoryResponse = attributeService.getAttributeCategory(dto);
        return attributeCategoryResponse;
    }

    @GetMapping("/product")
    public AttributeProductResponse getAttributeProduct(@RequestBody @Valid AttributeProductRequest dto) {
        log.info("AttributeController getAttributeProduct dto {}", dto);
        return attributeService.getAttributeProduct(dto);
    }
}
