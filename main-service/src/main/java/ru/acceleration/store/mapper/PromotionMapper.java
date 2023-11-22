package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.PromotionDto;
import ru.acceleration.store.model.Promotion;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PromotionMapper {

    PromotionDto toPromotionDto(Promotion promotion);

    Promotion toPromotion(PromotionDto promotionDto);
}
