package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.dto.PromotionDto;
import ru.acceleration.store.mapper.PromotionMapper;
import ru.acceleration.store.model.Promotion;
import ru.acceleration.store.model.Sale;
import ru.acceleration.store.repository.PromotionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;
    private final SaleService saleService;

    @Override
    public PromotionDto createPromotion(PromotionDto promotionDto) {
        Promotion newPromotion = promotionRepository.save(promotionMapper.toPromotion(promotionDto));
        log.info("Created new Promotion: {}", newPromotion);
        return promotionMapper.toPromotionDto(newPromotion);
    }

    @Transactional
    @Override
    public void deletePromotion(Long promotionId) {
        Promotion promotion = promotionRepository.getExistingPromotion(promotionId);
        List<Sale> salesInPromotion = saleService.getSalesByPromotion(promotionId);
        if (salesInPromotion.isEmpty()) {
            promotionRepository.delete(promotion);
            log.info("Deleted promotion with ID: {}", promotionId);
        } else {
            for (Sale sale : salesInPromotion) {
                sale.setPromotion(null);
                saleService.saveSaleToDatabase(sale);
            }
            promotionRepository.delete(promotion);
            log.info("Deleted promotion with ID: {}. Unlinked sales from promotion. ", promotionId);
        }
    }

    @Override
    public List<PromotionDto> getPromotions() {
        return promotionRepository
                .findAll()
                .stream()
                .map(promotionMapper::toPromotionDto)
                .collect(Collectors.toList());
    }

    @Override
    public PromotionDto getPromotion(Long promotionId) {
        Promotion foundPromotion = promotionRepository.getExistingPromotion(promotionId);
        return promotionMapper.toPromotionDto(foundPromotion);
    }

    @Override
    public PromotionDto editPromotion(Long promotionId, PromotionDto newPromotionDto) {
        Promotion existingPromotion = promotionRepository.getExistingPromotion(promotionId);
        existingPromotion.setName(newPromotionDto.getName());
        Promotion updatedPromotion = promotionRepository.save(existingPromotion);
        return promotionMapper.toPromotionDto(updatedPromotion);
    }
}
