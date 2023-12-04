package ru.acceleration.store.service.basket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.basket.BasketResponseDto;
import ru.acceleration.store.dto.model.ModelShortDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.BasketMapper;
import ru.acceleration.store.mapper.ModelMapper;
import ru.acceleration.store.model.Basket;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.ModelSet;
import ru.acceleration.store.model.User;
import ru.acceleration.store.repository.BasketRepo;
import ru.acceleration.store.repository.ModelRepository;
import ru.acceleration.store.repository.ModelSetRepository;
import ru.acceleration.store.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final UserRepository userRepository;
    private final BasketMapper basketMapper;
    private final BasketRepo basketRepo;
    private final ModelRepository modelRepository;
    private final ModelMapper productMapper;
    private final ModelSetRepository modelSetRepository;

    @Override
    public BasketResponseDto addProductToBasket(Long productId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new DataNotFoundException("user with id: " + userId + " not found"));
        Model model = modelRepository.findById(productId).orElseThrow(()
                -> new DataNotFoundException("product with id: " + productId + " not found"));
        if (basketRepo.findBasketByUserId(userId).isEmpty()) {
            ModelSet modelSet = new ModelSet();
            modelSet.setModel(model);
            Basket basket = new Basket();
            basket.setUser(user);
            basket.setModelSets(new ArrayList<>());
            basket.getModelSets().add(modelSet);
            modelSetRepository.save(modelSet);
            basketRepo.save(basket);
            return basketMapper.toBasketResponseDto(basket);
        } else {
            Basket basket = basketRepo.findBasketByUserId(userId).orElseThrow(()
                    -> new DataNotFoundException("basket for user with id: " + userId + "not found"));
            List<ModelSet> modelSetList = basket.getModelSets();
            Optional<ModelSet> modelSet = modelSetList.stream()
                    .filter(modelSet1 -> modelSet1.getModel().getId().equals(model.getId()))
                    .findAny();
            if (modelSet.isPresent()) {
                ModelSet modelSet1 = modelSet.get();
                modelSet1.setCount(modelSet1.getCount() + 1);
                modelSetRepository.save(modelSet1);
                return basketMapper.toBasketResponseDto(basket);
            }
//            for (ModelSet modelSet : modelSetList) {
//                if (modelSet.getModel().getId().equals(model.getId())) {
//                    modelSet.setCount(modelSet.getCount() + 1);
//                    modelSetRepository.save(modelSet);
//                    return basketMapper.toBasketResponseDto(basket);
//                }
//            }
            ModelSet newModelSet = new ModelSet();
            newModelSet.setModel(model);
            basket.getModelSets().add(newModelSet);
            modelSetRepository.save(newModelSet);
            basketRepo.save(basket);
            return basketMapper.toBasketResponseDto(basket);
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
        Model product = modelRepository.findById(productId).orElseThrow(()
                -> new DataNotFoundException("product with id: " + productId + " not found"));
        ModelShortDto modelFullDto = productMapper.toModelShortDto(product);
        BasketResponseDto basketResponseDto = basketMapper.toBasketResponseDto(basket);
        basketResponseDto.getModelSetResponseDtos().remove(modelFullDto);
        return basketMapper.toBasketResponseDto(basketRepo.save(basketMapper.toBasket(basketResponseDto)));
    }
}
