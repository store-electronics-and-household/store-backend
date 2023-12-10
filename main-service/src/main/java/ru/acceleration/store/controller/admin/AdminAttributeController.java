package ru.acceleration.store.controller.admin;

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
import ru.acceleration.store.dto.attribute.AttributeDtoRequest;
import ru.acceleration.store.dto.attribute.AttributeDtoResponse;
import ru.acceleration.store.service.attribute.AttributeService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(path = "/admin/attributes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminAttributeController {

    AttributeService attributeService;

    @GetMapping(path = "/{attributeId}")
    public ResponseEntity<AttributeDtoResponse> getAttributeById(
            @PathVariable Long attributeId) {
        log.info("GET: /admin/attributes/{} request", attributeId);
        AttributeDtoResponse attributeDtoResponse = attributeService.getAttributeById(attributeId);
        return ResponseEntity.ok().body(attributeDtoResponse);
    }

    @GetMapping()
    public ResponseEntity<List<AttributeDtoResponse>> findAttributes(
            @RequestParam(value = "text") String text,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero int from,
            @RequestParam(required = false, defaultValue = "10") @Positive int size)
    {
        log.info("GET: /admin/attributes request: text={},from={}, size={}", text, from, size);
        List<AttributeDtoResponse> attributeDtoResponseList = attributeService.findAttributes(text, from, size);
        return ResponseEntity.ok().body(attributeDtoResponseList);
    }

    @PostMapping()
    public ResponseEntity<AttributeDtoResponse> createAttribute(
            @RequestBody @Valid AttributeDtoRequest attributeDtoRequest)
    {
        log.info("POST: /admin/attributes request: {}", attributeDtoRequest);
        AttributeDtoResponse attributeDtoResponse = attributeService.createAttribute(attributeDtoRequest);
        return ResponseEntity.status(201).body(attributeDtoResponse);
    }

    @PutMapping(path = "/{attributesId}")
    public ResponseEntity<AttributeDtoResponse> updateAttribute(
            @PathVariable Long attributesId,
            @RequestBody @Valid AttributeDtoRequest attributeDtoRequest)
    {
        log.info("PATCH: /admin/attributes/{} request: {}",attributesId, attributeDtoRequest);
        AttributeDtoResponse attributeDtoResponse = attributeService.updateAttribute(attributeDtoRequest, attributesId);
        return ResponseEntity.ok().body(attributeDtoResponse);
    }

    @DeleteMapping(path = "/{attributesId}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable Long attributesId)
    {
        log.info("DELETE: /admin/attributes/{} request",attributesId);
        attributeService.deleteAttribute(attributesId);
        return ResponseEntity.noContent().build();
    }
}
