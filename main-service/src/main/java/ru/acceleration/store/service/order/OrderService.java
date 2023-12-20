package ru.acceleration.store.service.order;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.order.OrderRequestDto;
import ru.acceleration.store.dto.order.OrderResponseDto;

@Service
public interface OrderService {

    OrderResponseDto postOrder(OrderRequestDto orderRequestDto, Long userInfoId);

    OrderResponseDto getOrder(Long userInfoId, Long basketId);
}
