package ru.acceleration.store.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.basket.BasketResponseDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasketMapper {

    @Mapping(source = "userId", target = "user.id")
    Basket toBasket(BasketResponseDto basketResponseDto);

    @Mapping(source = "user.id", target = "userId")
    BasketResponseDto toBasketResponseDto(Basket basket);
}
