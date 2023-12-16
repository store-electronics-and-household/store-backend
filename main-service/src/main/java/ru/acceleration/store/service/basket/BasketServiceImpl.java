package ru.acceleration.store.service.basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    public BasketResponseDto addModelToBasket(Long modelId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new DataNotFoundException("user with id: " + userId + " not found"));
        Model model = modelRepository.findById(modelId).orElseThrow(()
                -> new DataNotFoundException("product with id: " + modelId + " not found"));
        if (basketRepo.findBasketByUserId(userId).isEmpty()) {
            ModelSet modelSet = new ModelSet();
            modelSet.setModel(model);
            Basket basket = new Basket();
            basket.setUser(user);
            basket.setModelSets(new ArrayList<>());
            modelSet.setProducts(new ArrayList<>());
            basket.getModelSets().add(modelSet);
            modelSet.setBasket(basket);
            modelSetRepository.save(modelSet);
            basketRepo.save(basket);
            return basketMapper.toBasketResponseDto(basket);
        } else {
            Basket basket = basketRepo.findBasketByUserId(userId).orElseThrow(()
                    -> new DataNotFoundException("basket for user with id: " + userId + "not found"));
//            List<ModelSet> modelSetList = basket.getModelSets();
//            Optional<ModelSet> modelSet = modelSetList.stream()
//                    .filter(modelSet1 -> modelSet1.getModel().getId().equals(model.getId()))
//                    .findAny();
            if (modelSetRepository.findByModelIdAndBasketId(model.getId(), basket.getId()).isPresent()) {
                ModelSet modelSet = modelSetRepository.findByModelIdAndBasketId(model.getId(), basket.getId()).get();
                modelSet.setCount(modelSet.getCount() + 1);
                modelSet.setBasket(basket);
                modelSetRepository.save(modelSet);
                return basketMapper.toBasketResponseDto(basket);
            }
            ModelSet newModelSet = new ModelSet();
            newModelSet.setModel(model);
            basket.getModelSets().add(newModelSet);
            newModelSet.setBasket(basket);
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

    @Override
    @Transactional
    public BasketResponseDto plusCountModelSet(Long modelSetId, Long userId) {
        Basket basket = basketRepo.findBasketByUserId(userId).orElseThrow(()
                -> new DataNotFoundException("basket for user with : " + userId + " not found"));
        ModelSet modelSet = modelSetRepository.findByIdAndBasketId(modelSetId, basket.getId()).orElseThrow(()
                -> new DataNotFoundException("modelSet with id : " + modelSetId + "in basket with id: "
                + basket.getId() + " not found"));
        modelSet.setCount(modelSet.getCount() + 1);
        modelSetRepository.save(modelSet);
        return basketMapper.toBasketResponseDto(basket);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BasketResponseDto minusCountModelSet(Long modelSetId, Long userId) {
        Basket basket = basketRepo.findBasketByUserId(userId).orElseThrow(()
                -> new DataNotFoundException("basket for user with : " + userId + " not found"));
        ModelSet modelSet = modelSetRepository.findByIdAndBasketId(modelSetId, basket.getId()).orElseThrow(()
                -> new DataNotFoundException("modelSet with id : " + modelSetId + "in basket with id: "
                + basket.getId() + " not found"));
//        if (modelSet.getCount() == 1) {
//            basket.getModelSets().remove(modelSet);
//            modelSetRepository.deleteById(modelSet.getId());
//        } else {
//            modelSet.setCount(modelSet.getCount() - 1);
//            modelSetRepository.save(modelSet);
//        }
        countValueCheck(basket, modelSet);
        return basketMapper.toBasketResponseDto(basket);
    }

    @Override
    @Transactional
    public BasketResponseDto plusCountModel(Long modelId, Long userId) {
        Basket basket = basketRepo.findBasketByUserId(userId).orElseThrow(()
                -> new DataNotFoundException("basket for user with : " + userId + " not found"));
        ModelSet modelSet = modelSetRepository.findByModelIdAndBasketId(modelId, basket.getId()).orElseThrow(()
                -> new DataNotFoundException("modelSet for model with id: " + modelId
                + " and basket id: " + basket.getId() + " not found"));
//        Model model = modelRepository.findById(modelId).orElseThrow(()
//                -> new DataNotFoundException("product with id: " + modelId + " not found"));
//        List<ModelSet> modelSetList = basket.getModelSets();
//        Optional<ModelSet> modelSetOptional = modelSetList.stream()
//                .filter(modelSet -> modelSet.getModel().getId().equals(model.getId()))
//                .findAny();
//        if (modelSetOptional.isPresent()) {
//            ModelSet presentModelSet = modelSetOptional.get();
//            presentModelSet.setCount(presentModelSet.getCount() + 1);
//            modelSetRepository.save(presentModelSet);
//        }
        modelSet.setCount(modelSet.getCount() + 1);
        modelSetRepository.save(modelSet);
        return basketMapper.toBasketResponseDto(basket);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BasketResponseDto minusCountModel(Long modelId, Long userId) {
        Basket basket = basketRepo.findBasketByUserId(userId).orElseThrow(()
                -> new DataNotFoundException("basket for user with : " + userId + " not found"));
        ModelSet modelSet = modelSetRepository.findByModelIdAndBasketId(modelId, basket.getId()).orElseThrow(()
                -> new DataNotFoundException("modelSet for model with id: " + modelId
                + " and basket id: " + basket.getId() + " not found"));
//        if (modelSet.getCount() == 1) {
//            basket.getModelSets().remove(modelSet);
//            modelSetRepository.deleteById(modelSet.getId());
//        } else {
//            modelSet.setCount(modelSet.getCount() - 1);
//            modelSetRepository.save(modelSet);
//        }
        countValueCheck(basket, modelSet);
        return basketMapper.toBasketResponseDto(basket);
    }

    private void countValueCheck(Basket basket, ModelSet modelSet) {
        if (modelSet.getCount() == 1) {
            basket.getModelSets().remove(modelSet);
            modelSetRepository.deleteById(modelSet.getId());
        } else {
            modelSet.setCount(modelSet.getCount() - 1);
            modelSetRepository.save(modelSet);
        }
    }
}


