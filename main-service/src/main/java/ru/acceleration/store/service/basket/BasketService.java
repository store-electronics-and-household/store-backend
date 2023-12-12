package ru.acceleration.store.service.basket;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.basket.BasketResponseDto;


@Service
public interface BasketService {

    BasketResponseDto addModelToBasket(Long modelId, Long userId);

    BasketResponseDto getBasket(Long userId);

    BasketResponseDto removeModelSetFromBasket(Long modelSetId, Long userId);

    BasketResponseDto plusCountModelSet(Long modelSetId, Long userId);

    BasketResponseDto minusCountModelSet(Long modelSetId, Long userId);
}
