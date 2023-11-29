package ru.acceleration.store.service.basket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.basket.BasketResponseDto;
import ru.acceleration.store.dto.model.ModelFullDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.BasketMapper;
import ru.acceleration.store.repository.BasketRepo;
import ru.acceleration.store.repository.ModelRepository;
import ru.acceleration.store.repository.UserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final UserRepository userRepository;
    private final BasketMapper basketMapper;
    private final BasketRepo basketRepo;
    private final ModelRepository modelRepository;
    private final ProductMapper productMapper;

    @Override
    public BasketResponseDto addProductToBasket(Long productId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new DataNotFoundException("user with id: " + userId + " not found"));
        Product product = modelRepository.findById(productId).orElseThrow(()
                -> new DataNotFoundException("product with id: " + productId + " not found"));
        if (basketRepo.findBasketByUserId(userId).isEmpty()) {
            BasketResponseDto basketResponseDto = new BasketResponseDto();
            basketResponseDto.setUserId(user.getId());
            basketResponseDto.setProducts(new ArrayList<>());
            basketResponseDto.getProducts().add(productMapper.toProductFullDto(product));
            return basketMapper.toBasketResponseDto(basketRepo.save(basketMapper.toBasket(basketResponseDto)));
        } else {
            Basket basket = basketRepo.findBasketByUserId(userId).orElseThrow(()
                    -> new DataNotFoundException("basket for user with id " + userId + "not found"));
            System.out.println(basket);
            BasketResponseDto basketResponseDto = basketMapper.toBasketResponseDto(basket);
            basketResponseDto.setUserId(user.getId());
            basketResponseDto.getProducts().add(productMapper.toProductFullDto(product));
            return basketMapper.toBasketResponseDto(basketRepo.save(basketMapper.toBasket(basketResponseDto)));
        }
    }

    @Override
    public BasketResponseDto getBasket(Long basketId) {
        Basket basket = basketRepo.findById(basketId).orElseThrow(()
                -> new DataNotFoundException("basket with id: " + basketId + " not found"));
        return basketMapper.toBasketResponseDto(basket);
    }

    @Override
    public BasketResponseDto removeProductFromBasket(Long productId, Long basketId) {
       Basket basket = basketRepo.findById(basketId).orElseThrow(()
                -> new DataNotFoundException("basket with id: " + basketId + " not found"));
       Product product = modelRepository.findById(productId).orElseThrow(()
                -> new DataNotFoundException("product with id: " + productId + " not found"));
       ModelFullDto modelFullDto = productMapper.toProductFullDto(product);
       BasketResponseDto basketResponseDto = basketMapper.toBasketResponseDto(basket);
       basketResponseDto.getProducts().remove(modelFullDto);
       return basketMapper.toBasketResponseDto(basketRepo.save(basketMapper.toBasket(basketResponseDto)));
    }
}
