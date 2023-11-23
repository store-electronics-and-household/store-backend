package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.ProductShortDto;
import ru.acceleration.store.dto.PromotionDto;
import ru.acceleration.store.service.PromotionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/promotions")
@Slf4j
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping
    public List<PromotionDto> getPromotions() {
        log.info("GET /promotions");
        return promotionService.getPromotions();
    }

    /**
     * Метод передает список всех товаров со скидками, которые входят в определенную подборку
     *
     * @param promotionId - ID подборки
     * @return список акционных товаров, входящих в подборку
     */
    @GetMapping("/{promotionId}")
    public List<ProductShortDto> getPromotion(@PathVariable Long promotionId) {
        log.info("GET: /promotions/{}", promotionId);
        return promotionService.getPromotion(promotionId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PromotionDto createPromotion(@Valid @RequestBody PromotionDto promotionDto) {
        log.info("POST: /promotions");
        return promotionService.createPromotion(promotionDto);
    }

    @DeleteMapping("/{promotionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePromotion(@PathVariable Long promotionId) {
        log.info("DELETE: /promotions");
        promotionService.deletePromotion(promotionId);
    }

    @PatchMapping("/{promotionId}")
    public PromotionDto editPromotion(@PathVariable Long promotionId, @Valid @RequestBody PromotionDto newPromotionDto) {
        log.info("PATCH: /promotions/{}", promotionId);
        return promotionService.editPromotion(promotionId, newPromotionDto);
    }
}
