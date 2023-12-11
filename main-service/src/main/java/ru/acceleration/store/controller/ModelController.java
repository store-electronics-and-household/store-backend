package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.model.ModelFullDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.model.NewModelDto;
import ru.acceleration.store.service.model.ModelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class ModelController {

    private final ModelService modelService;

    @PostMapping("models/{categoryId}/model")
    public ResponseEntity<ModelShortDto> addModel(@PathVariable Long categoryId, @Valid @RequestBody NewModelDto newModelDto) {
        log.info("POST: /models");
        return ResponseEntity.status(201).body(modelService.addModel(categoryId, newModelDto));
    }

    @GetMapping("/models/{modelId}")
    public ResponseEntity<ModelFullDto> getModelById(@PathVariable Long modelId) {
        log.info("GET: /models/{}", modelId);
        return ResponseEntity.ok().body(modelService.getModelById(modelId));
    }

    @GetMapping("/category/{categoryId}/model")
    public ResponseEntity<Page<ModelShortDto>> getModelsByCategoryId(@PathVariable @Positive Long categoryId,
                                                                     @RequestParam(value = "from", defaultValue = "0")
                                                                     @PositiveOrZero Integer from,
                                                                     @RequestParam(value = "size", defaultValue = "20")
                                                                     @Positive Integer size,
                                                                     @RequestParam(defaultValue = "NAME") String sort) {
        log.info("GET: /models/{}?from=" + from + "&size=" + size + "&sort=" + sort, categoryId);
        return ResponseEntity.ok().body(modelService.getModelByCategory(categoryId, from, size, sort));
    }

    @GetMapping("/category/{categoryId}/models/popular")
    public ResponseEntity<List<ModelShortDto>> getPopularModelsByCategoryId(@PathVariable @Positive Long categoryId) {
        log.info("GET: /category/{}/models/popular" , categoryId);
        return ResponseEntity.ok().body(modelService.getPopularModelsByCategoryId(categoryId));
    }

    /**
     * Поиск товаров в поисковой строке сайта
     * Поиск осуществляется по наименовании товара, категории товара
     *
     * @param text текст для поиска
     * @param from номер страницы (начиная с 0). По умолчанию 0.
     * @param size количество элементов на странице. По умолчанию 10.
     * @param sort вариант сортировки: NAME, DESC_PRICE, ASC_PRICE. По умолчанию NAME.
     * @return список товаров, удовлетворяющих условиям поиска
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ModelShortDto>> searchModels(@RequestParam(value = "text") @NotBlank String text,
                                            @RequestParam(value = "from", defaultValue = "0")
                                            @PositiveOrZero Integer from,
                                            @RequestParam(value = "size", defaultValue = "20")
                                            @Positive Integer size,
                                            @RequestParam(defaultValue = "NAME") String sort) {
        log.info("GET: /search?text=" + text + "&from=" + from + "&size=" + size + "&sort=" + sort);
        return ResponseEntity.ok().body(modelService.searchModels(text, from, size, sort));
    }
}
