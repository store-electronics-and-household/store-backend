package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.attribute.*;
import ru.acceleration.store.service.attribute.AttributeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AttributeController {

    private final AttributeService attributeService;


    @PostMapping("/categories/{categoryId}/category-attributes")
    public ResponseEntity<CategoryAttributeDtoResponse> postCategoryAttribute(@PathVariable Long categoryId,
                                                                              @RequestParam Long attributeId,
                                                                              @Valid @RequestBody CategoryAttributeDtoRequest categoryAttributeDtoRequest) {
        log.info("POST: /categories/{}/category-attributes", categoryId);
        return ResponseEntity.status(201).body(attributeService.postCategoryAttribute(categoryId, attributeId, categoryAttributeDtoRequest));
    }

    @PostMapping("/models/{modelId}/model-attributes")
    public ResponseEntity<ModelAttributeDtoResponse> postModelAttribute(@PathVariable Long modelId,
                                                                        @RequestParam Long categoryAttributeId,
                                                                        @Valid @RequestBody ModelAttributeDtoRequest modelAttributeDtoRequest) {
        log.info("POST: /models/{}/model-attributes", modelId);
        return ResponseEntity.status(201).body(attributeService.postModelAttribute(modelId, categoryAttributeId, modelAttributeDtoRequest));
    }

    @GetMapping("models/{modelId}/model-attributes")
    public ResponseEntity<List<ModelAttributeDtoResponse>> getAllModelAttributeByModelId(@PathVariable Long modelId) {
        log.info("GET: /models/{}/model-attributes", modelId);
        return ResponseEntity.ok().body(attributeService.getAllModelAttributeByModelId(modelId));
    }
//    @GetMapping("/category")
//    public AttributeCategoryResponse getAttributeCategory(@RequestBody @Valid AttributeCategoryRequest dto) {
//        log.info("AttributeController postUser dto {}", dto);
//        AttributeCategoryResponse attributeCategoryResponse = attributeService.getAttributeCategory(dto);
//        return attributeCategoryResponse;
//    }
//
//    @GetMapping("/product")
//    public AttributeProductResponse getAttributeProduct(@RequestBody @Valid AttributeProductRequest dto) {
//        log.info("AttributeController getAttributeProduct dto {}", dto);
//        return attributeService.getAttributeProduct(dto);
//    }
}
