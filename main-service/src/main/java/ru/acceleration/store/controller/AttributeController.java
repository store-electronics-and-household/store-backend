package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.attribute.*;
import ru.acceleration.store.service.attribute.AttributeService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AttributeController {

    AttributeService attributeService;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/attributes/{attributeId}")
    public ResponseEntity<AttributeDtoResponse> getAttributeById(
            @PathVariable Long attributeId) {
        log.info("GET: /attributes/{} request", attributeId);
        AttributeDtoResponse attributeDtoResponse = attributeService.getAttributeById(attributeId);
        log.info("GET: /attributes/{} completed: {}", attributeId, attributeDtoResponse);
        return ResponseEntity.ok().body(attributeDtoResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/attributes")
    public ResponseEntity<List<AttributeDtoResponse>> findAttributes(
            @RequestParam(value = "text") String text,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero int from,
            @RequestParam(required = false, defaultValue = "10") @Positive int size)
    {
        log.info("GET: /attributes request: text={},from={}, size={}", text, from, size);
        List<AttributeDtoResponse> attributeDtoResponseList = attributeService.findAttributes(text, from, size);
        log.info("GET: /attributes completed: text={},from={}, size={}", text, from, size);
        return ResponseEntity.ok().body(attributeDtoResponseList);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/attributes")
    public ResponseEntity<AttributeDtoResponse> createAttribute(
            @RequestBody @Valid AttributeDtoRequest attributeDtoRequest)
    {
        log.info("POST: /attributes request: {}", attributeDtoRequest);
        AttributeDtoResponse attributeDtoResponse = attributeService.createAttribute(attributeDtoRequest);
        log.info("POST: /attributes completed: {}", attributeDtoResponse);
        return ResponseEntity.status(201).body(attributeDtoResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(path = "/attributes/{attributesId}")
    public ResponseEntity<AttributeDtoResponse> patchAttribute(
            @PathVariable Long attributesId,
            @RequestBody @Valid AttributeDtoRequest attributeDtoRequest)
    {
        log.info("PATCH: /attributes/{} request: {}",attributesId, attributeDtoRequest);
        AttributeDtoResponse attributeDtoResponse = attributeService.patchAttribute(attributeDtoRequest, attributesId);
        log.info("PATCH: /attributes/{} completed: {}",attributesId, attributeDtoResponse);
        return ResponseEntity.ok().body(attributeDtoResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path = "/attributes/{attributesId}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable Long attributesId)
    {
        log.info("DELETE: /attributes/{} request",attributesId);
        attributeService.deleteAttribute(attributesId);
        log.info("PATCH: /attributes/{} completed",attributesId);
        return ResponseEntity.noContent().build();
    }
}
