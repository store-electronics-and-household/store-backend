package ru.acceleration.store.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.order.OrderRequestDto;
import ru.acceleration.store.dto.order.OrderResponseDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.OrderMapper;
import ru.acceleration.store.model.Basket;
import ru.acceleration.store.model.Order;
import ru.acceleration.store.model.User;
import ru.acceleration.store.model.enums.BasketStatus;
import ru.acceleration.store.repository.BasketRepo;
import ru.acceleration.store.repository.OrderRepository;
import ru.acceleration.store.repository.UserRepository;


@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BasketRepo basketRepo;
    private final UserRepository userRepository;

    @Override
    public OrderResponseDto postOrder(OrderRequestDto orderRequestDto, Long userInfoId) {
        User user = userRepository.findByUserInfoId(userInfoId).orElseThrow(()
                -> new DataNotFoundException("user for userInfo with id: " + userInfoId + " not found"));
        Basket basket = basketRepo.findBasketByUserIdAndBasketStatusActive(user.getId()).orElseThrow(()
                -> new DataNotFoundException("basket for user with id: " + user.getId() + " not found"));
        Order order = orderMapper.toOrder(orderRequestDto);
        order.setBasket(basket);
        orderRepository.save(order);
        basket.setBasketStatus(BasketStatus.SAVED);
        basketRepo.save(basket);
        return orderMapper.toOrderResponseDto(order);
    }

    @Override
    public OrderResponseDto getOrder(Long userInfoId, Long basketId) {
        User user = userRepository.findByUserInfoId(userInfoId).orElseThrow(()
                -> new DataNotFoundException("user for userInfo with id: " + userInfoId + " not found"));
        Basket basket = basketRepo.findBasketByIdAndUserIdAndBasketStatusSaved(user.getId(), basketId).orElseThrow(()
                -> new DataNotFoundException("basket for user with id: " + user.getId() + " not found"));
        Order order = orderRepository.findOrderByBasketId(basket.getId()).orElseThrow(()
                -> new DataNotFoundException("order for basket with id: " + basket.getId() + " not found"));
        return orderMapper.toOrderResponseDto(order);
    }
}
