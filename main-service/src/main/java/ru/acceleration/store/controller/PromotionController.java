package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.product.ProductShortDto;
import ru.acceleration.store.dto.promotion.PromotionDto;
import ru.acceleration.store.service.promotion.PromotionService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/promotions")
@Slf4j
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping
    public ResponseEntity<List<PromotionDto>> getPromotions() {
        log.info("GET /promotions");
        return ResponseEntity.ok().body(promotionService.getPromotions());
    }

    /**
     * Метод передает список всех товаров со скидками, которые входят в определенную подборку
     *
     * @param promotionId - ID подборки
     * @return список акционных товаров, входящих в подборку
     */
    @GetMapping("/{promotionId}")
    public ResponseEntity<List<ProductShortDto>> getPromotion(@PathVariable Long promotionId) {
        log.info("GET: /promotions/{}", promotionId);
        return ResponseEntity.ok().body(promotionService.getPromotion(promotionId));
    }

    @PostMapping
    public ResponseEntity<PromotionDto> createPromotion(@Valid @RequestBody PromotionDto promotionDto) {
        log.info("POST: /promotions");
        return ResponseEntity.status(201).body(promotionService.createPromotion(promotionDto));
    }

    @DeleteMapping("/{promotionId}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long promotionId) {
        log.info("DELETE: /promotions");
        promotionService.deletePromotion(promotionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{promotionId}")
    public ResponseEntity<PromotionDto> editPromotion(@PathVariable Long promotionId, @Valid @RequestBody PromotionDto newPromotionDto) {
        log.info("PATCH: /promotions/{}", promotionId);
        return ResponseEntity.ok().body(promotionService.editPromotion(promotionId, newPromotionDto));
    }
}
