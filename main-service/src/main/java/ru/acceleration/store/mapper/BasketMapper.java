package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.acceleration.store.dto.basket.BasketGetResponseDto;
import ru.acceleration.store.dto.basket.BasketResponseDto;
import ru.acceleration.store.model.Basket;
import ru.acceleration.store.service.user.UserService;

@Mapper(componentModel = "spring",
        uses = {UserService.class, ModelSetMapper.class, ModelMapper.class})
public interface BasketMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "modelSetResponseDtos", target = "modelSets")
    Basket toBasket(BasketResponseDto basketResponseDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "modelSets", target = "modelSetResponseDtos")
    BasketResponseDto toBasketResponseDto(Basket basket);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "modelSets", target = "modelSetResponseDtos")
    BasketGetResponseDto toBasketGetResponseDto(Basket basket);
}
