package ru.acceleration.store.controller.admin;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoRequest;
import ru.acceleration.store.dto.attribute.ModelAttributeDtoResponse;
import ru.acceleration.store.service.attribute.ModelAttributesService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(path = "/admin/model-attributes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminModelAttributesController {

    ModelAttributesService modelAttributesService;

    @GetMapping(path = "/model/{modelId}")
    public ResponseEntity<List<ModelAttributeDtoResponse>> getModelAttributesByModelId(
            @PathVariable Long modelId) {
        log.info("GET: /admin/model-attributes/model/{} request", modelId);
        List<ModelAttributeDtoResponse> modelAttributeDtoResponse =
                modelAttributesService.getModelAttributesByModelId(modelId);
        return ResponseEntity.ok().body(modelAttributeDtoResponse);
    }

    @GetMapping(path = "/{modelAttributesId}")
    public ResponseEntity<ModelAttributeDtoResponse> getModelAttributesId(
            @PathVariable Long modelAttributesId) {
        log.info("GET: /admin/category-attributes/{} request", modelAttributesId);
        ModelAttributeDtoResponse modelAttributeDtoResponse =
                modelAttributesService.getModelAttributesById(modelAttributesId);
        return ResponseEntity.ok().body(modelAttributeDtoResponse);
    }

    @PostMapping(path = "/model/{modelId}")
    public ResponseEntity<ModelAttributeDtoResponse> createModelAttributeResponse(
            @PathVariable Long modelId,
            @RequestParam Long categoryAttributeId,
            @Valid @RequestBody ModelAttributeDtoRequest modelAttributeDtoRequest) {
        log.info("POST: /admin/category-attributes/model/{}?categoryAttributeId={} request, dto={}",
                modelId, categoryAttributeId, modelAttributeDtoRequest);
        ModelAttributeDtoResponse modelAttributeDtoResponse =
                modelAttributesService.createModelAttribute(modelId, categoryAttributeId, modelAttributeDtoRequest);
        return ResponseEntity.status(201).body(modelAttributeDtoResponse);
    }

    @PutMapping(path = "/{modelAttributeId}")
    public ResponseEntity<ModelAttributeDtoResponse> updateModelAttributeResponse(
            @PathVariable Long modelAttributeId,
            @RequestParam Long modelId,
            @RequestParam Long categoryAttributeId,
            @Valid @RequestBody ModelAttributeDtoRequest modelAttributeDtoRequest) {
        log.info("PUT: /admin/category-attributes/{}?modelId={}&categoryAttributeId={} request, dto={}",
                modelAttributeId, modelId, categoryAttributeId, modelAttributeDtoRequest);
        ModelAttributeDtoResponse modelAttributeDtoResponse =
                modelAttributesService.updateModelAttribute(modelAttributeId, modelId, categoryAttributeId, modelAttributeDtoRequest);
        return ResponseEntity.ok().body(modelAttributeDtoResponse);
    }

    @DeleteMapping(path = "/{modelAttributeId}")
    public ResponseEntity<?> deleteModelAttributeResponse(
            @PathVariable Long modelAttributeId) {
        log.info("DELETE: /admin/category-attributes/{} request", modelAttributeId);
        modelAttributesService.deleteModelAttribute(modelAttributeId);
        return ResponseEntity.noContent().build();
    }
}
