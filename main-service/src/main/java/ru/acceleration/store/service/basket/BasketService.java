package ru.acceleration.store.service.basket;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.basket.BasketResponseDto;


@Service
public interface BasketService {

    BasketResponseDto addModelToBasket(Long modelId, Long userId);

    BasketResponseDto getBasket(Long userId);

    BasketResponseDto removeModelFromBasket(Long modelId, Long userId);
}
