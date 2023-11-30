package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.model.ModelFullDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.service.model.ModelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/models")
@Slf4j
public class ModelController {

    private final ModelService modelService;

    @PostMapping
    public ResponseEntity<ModelShortDto> addModel(@Valid @RequestBody NewModelDto newModelDto) {
        log.info("POST: /models");
        return ResponseEntity.status(201).body(modelService.addModel(newModelDto));
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<ModelFullDto> getModelById(@PathVariable Long modelId) {
        log.info("GET: /models/{}", modelId);
        return ResponseEntity.ok().body(modelService.getModelById(modelId));
    }

    @GetMapping("/category/{categoryId}/model")
    public ResponseEntity<List<ModelShortDto>> getModelsByCategoryId(@PathVariable Long categoryId,
                                                                     @RequestParam(value = "from", defaultValue = "0")
                                                                     @PositiveOrZero Integer from,
                                                                     @RequestParam(value = "size", defaultValue = "10")
                                                                     @Positive Integer size,
                                                                     @RequestParam(defaultValue = "NAME") String sort) {
        log.info("GET: /models/{}?=" + from + "&size=" + size, categoryId);
        return ResponseEntity.ok().body(modelService.getModelByCategory(categoryId, from, size, sort));
    }
}
