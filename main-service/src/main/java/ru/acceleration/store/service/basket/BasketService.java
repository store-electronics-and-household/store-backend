package ru.acceleration.store.service.basket;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.basket.BasketResponseDto;


@Service
public interface BasketService {

    BasketResponseDto addProductToBasket(Long modelId, Long userId);

    BasketResponseDto getBasket(Long userId);

    BasketResponseDto removeProductFromBasket(Long modelId, Long userId);
}
