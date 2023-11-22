package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.PromotionDto;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    @Override
    public PromotionDto createPromotion(PromotionDto promotionDto) {
        return null;
    }

    @Override
    public void deletePromotion(Long promotionId) {

    }

    @Override
    public List<PromotionDto> getPromotions() {
        return null;
    }

    @Override
    public PromotionDto getPromotion(Long promotionId) {
        return null;
    }

    @Override
    public PromotionDto editPromotion(Long promotionId, PromotionDto newPromotionDto) {
        return null;
    }
}
