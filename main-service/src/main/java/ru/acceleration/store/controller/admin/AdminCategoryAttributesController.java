package ru.acceleration.store.controller.admin;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.CategoryAttributeDtoResponse;
import ru.acceleration.store.service.attribute.CategoryAttributesService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(path = "/admin/category-attributes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminCategoryAttributesController {

    CategoryAttributesService categoryAttributesService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryAttributeDtoResponse> getCategoryAttributeById(
            @PathVariable Long id)
    {
        log.info("GET: /admin/category-attributes/{} request", id);
        CategoryAttributeDtoResponse dtoResponse = categoryAttributesService.getCategoryAttributeById(id);
        return ResponseEntity.ok().body(dtoResponse);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryAttributeDtoResponse>> getAllCategoryAttributesByCategoryId(
            @RequestParam Long categoryId)
    {
        log.info("GET: /admin/category-attributes?categoryId={} request", categoryId);
        var list = categoryAttributesService.getCategoryAttributesByCategoryId(categoryId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryAttributeDtoResponse> createCategoryAttribute(
            @PathVariable Long categoryId,
            @RequestParam Long attributeId,
            @RequestBody @Valid CategoryAttributeDtoRequest categoryAttributeDtoRequest)
    {
        log.info("POST: /admin/category-attributes request: categoryId={}, attributeID={}, dto={}",
                categoryId, attributeId, categoryAttributeDtoRequest);
        var dtoResponse = categoryAttributesService.createCategoryAttributes(categoryId, attributeId, categoryAttributeDtoRequest);
        return ResponseEntity.status(201).body(dtoResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CategoryAttributeDtoResponse> updateCategoryAttribute(
            @RequestBody @Valid CategoryAttributeDtoRequest categoryAttributeDtoRequest,
            @PathVariable Long id,
            @RequestParam Long categoryId,
            @RequestParam Long attributeId)
    {
        log.info("PUT: /admin/category-attributes/{id} request: dto={}, id={}, categoryId={}, attributeId={}",
                categoryAttributeDtoRequest, id, categoryId, attributeId);
        var dtoResponse = categoryAttributesService.updateCategoryAttribute(
                id, categoryId, attributeId, categoryAttributeDtoRequest);
        return ResponseEntity.ok().body(dtoResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCategoryAttributeById(
            @PathVariable Long id)
    {
        log.info("DELETE: /admin/category-attributes/{id} request: id={}", id);
        categoryAttributesService.deleteCategoryAttributeById(id);
        return ResponseEntity.noContent().build();
    }
}
