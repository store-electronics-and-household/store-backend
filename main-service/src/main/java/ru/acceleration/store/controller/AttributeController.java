package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.*;
import ru.acceleration.store.service.AttributeService;
import ru.acceleration.store.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/attribute")
@Slf4j
public class AttributeController {

    private final AttributeService attributeService;

    @GetMapping("/category")
    public AttributeResponse getAttributeCategory(@RequestBody @Valid AttributeCategoryRequest dto) {
        log.info("AttributeController postUser dto {}", dto);
        AttributeResponse attributeResponse=attributeService.getAttributeCategory(dto);
        return null;
    }

    @GetMapping("/product")
    public AttributeResponse getAttributeProduct(@RequestBody @Valid AttributeProductRequest dto) {
        log.info("AttributeController getAttributeProduct dto {}", dto);
        return attributeService.getAttributeProduct(dto);
    }
}
