package ru.acceleration.store.service.basket;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.basket.BasketResponseDto;


@Service
public interface BasketService {

    BasketResponseDto addProductToBasket(Long productId, Long userId);

    BasketResponseDto getBasket(Long basketId);

    BasketResponseDto removeProductFromBasket(Long productId, Long basketId);
}
