package ru.acceleration.store.service.basket;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.basket.BasketResponseDto;


@Service
public interface BasketService {

    BasketResponseDto addModelToBasket(Long modelId, Long userInfoId);

    BasketResponseDto getBasket(Long userInfoId);

    BasketResponseDto removeModelSetFromBasket(Long modelSetId, Long userInfoId);

    BasketResponseDto plusCountModelSet(Long modelSetId, Long userInfoId);

    BasketResponseDto minusCountModelSet(Long modelSetId, Long userInfoId);

    BasketResponseDto plusCountModel(Long modelId, Long userInfoId);

    BasketResponseDto minusCountModel(Long modelId, Long userInfoId);
}
