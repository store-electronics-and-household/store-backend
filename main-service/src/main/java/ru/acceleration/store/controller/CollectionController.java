package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.collection.UpdateCollectionDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.dto.collection.CollectionDto;
import ru.acceleration.store.service.collection.CollectionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/collections")
@Slf4j
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping
    public ResponseEntity<List<CollectionDto>> getCollections() {
        log.info("GET /collections");
        return ResponseEntity.ok().body(collectionService.getCollections());
    }

    /**
     * Метод передает список всех товаров, которые входят в определенную подборку
     *
     * @param collectionID - ID подборки
     * @param sort         - Вариант сортировки: NAME, DESC_PRICE, ASC_PRICE. По умолчанию NAME.
     * @return список акционных товаров, входящих в подборку
     */
    @GetMapping("/{collectionID}")
    public ResponseEntity<List<ModelShortDto>> getCollection(@PathVariable Long collectionID,
                                                             @RequestParam(defaultValue = "NAME") String sort) {
        log.info("GET: /collections/{}?sort=" + sort, collectionID);
        return ResponseEntity.ok().body(collectionService.getCollection(collectionID, sort));
    }

    @PostMapping
    public ResponseEntity<CollectionDto> createCollection(@Valid @RequestBody CollectionDto collectionDto) {
        log.info("POST: /collections");
        return ResponseEntity.status(201).body(collectionService.createCollection(collectionDto));
    }

    @DeleteMapping("/{collectionId}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long collectionId) {
        log.info("DELETE: /collections");
        collectionService.deleteCollection(collectionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{collectionId}")
    public ResponseEntity<CollectionDto> editCollection(@PathVariable Long collectionId, @Valid @RequestBody UpdateCollectionDto updateCollectionDto) {
        log.info("PATCH: /collections/{}", collectionId);
        return ResponseEntity.ok().body(collectionService.editCollection(collectionId, updateCollectionDto));
    }

    @PostMapping("/{collectionId}")
    public ResponseEntity<Void> addModelToCollection(@PathVariable Long collectionId, @RequestParam Long modelId) {
        log.info("POST: /collections/{}?modelId={}", collectionId, modelId);
        collectionService.addModelToCollection(collectionId, modelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
