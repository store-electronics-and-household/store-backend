package ru.acceleration.store.service.basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.dto.basket.BasketResponseDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.BasketMapper;
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
    private final ModelSetRepository modelSetRepository;

    @Override
    @Transactional
    public BasketResponseDto addModelToBasket(Long productId, Long userId) {
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
            ModelSet newModelSet = new ModelSet();
            newModelSet.setModel(model);
            basket.getModelSets().add(newModelSet);
            modelSetRepository.save(newModelSet);
            basketRepo.save(basket);
            return basketMapper.toBasketResponseDto(basket);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BasketResponseDto getBasket(Long userId) {
        Basket basket = basketRepo.findBasketByUserId(userId).orElseThrow(()
                -> new DataNotFoundException("basket for user with : " + userId + " not found"));
        return basketMapper.toBasketResponseDto(basket);
    }

    @Override
    @Transactional
    public BasketResponseDto removeModelSetFromBasket(Long modelSetId, Long userId) {
        Basket basket = basketRepo.findBasketByUserId(userId).orElseThrow(()
                -> new DataNotFoundException("basket for user with : " + userId + " not found"));
        ModelSet modelSet = modelSetRepository.findById(modelSetId).orElseThrow(()
                -> new DataNotFoundException("modelSet with id: " + modelSetId + " not found"));
        basket.getModelSets().remove(modelSet);
        modelSetRepository.deleteById(modelSet.getId());
        return basketMapper.toBasketResponseDto(basket);
    }
}

