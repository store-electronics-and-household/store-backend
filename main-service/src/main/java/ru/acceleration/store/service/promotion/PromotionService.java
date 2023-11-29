package ru.acceleration.store.service.promotion;

import ru.acceleration.store.dto.product.ProductShortDto;
import ru.acceleration.store.dto.promotion.PromotionDto;
import ru.acceleration.store.model.Promotion;

import java.util.List;

public interface PromotionService {

    PromotionDto createPromotion(PromotionDto promotionDto);

    void deletePromotion(Long promotionId);

    List<PromotionDto> getPromotions();

    List<ProductShortDto> getPromotion(Long promotionId);

    PromotionDto editPromotion(Long promotionId, PromotionDto newPromotionDto);

    Promotion getPromotionById(Long promotionId);

}
