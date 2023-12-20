package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.acceleration.store.dto.order.OrderRequestDto;
import ru.acceleration.store.dto.order.OrderResponseDto;
import ru.acceleration.store.model.Order;

@Mapper(componentModel = "spring", uses = {BasketMapper.class, ModelMapper.class})
public interface OrderMapper {

    @Mapping(target = "orderBasket", source = "basket")
    @Mapping(target = "paymentId", source = "payment.id")
    @Mapping(target = "addressId", source = "userAddress.id")
    OrderResponseDto toOrderResponseDto(Order order);

    Order toOrder(OrderRequestDto orderRequestDto);
}
