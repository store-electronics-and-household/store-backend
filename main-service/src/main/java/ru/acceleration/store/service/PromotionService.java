package ru.acceleration.store.service;

import ru.acceleration.store.dto.PromotionDto;
import java.util.List;

public interface PromotionService {

    PromotionDto createPromotion(PromotionDto promotionDto);

    void deletePromotion(Long promotionId);

    List<PromotionDto> getPromotions();

    PromotionDto getPromotion(Long promotionId);

    PromotionDto editPromotion(Long promotionId, PromotionDto newPromotionDto);

}
