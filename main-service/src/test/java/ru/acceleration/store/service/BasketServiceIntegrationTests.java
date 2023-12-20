package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.dto.basket.BasketGetResponseDto;
import ru.acceleration.store.dto.order.OrderRequestDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.repository.OrderRepository;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.service.UserInfoService;
import ru.acceleration.store.service.basket.BasketService;
import ru.acceleration.store.service.order.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
@Sql({"/schema-test.sql", "/data-for-basket-tests.sql"})
@ActiveProfiles("test")
@Transactional
public class BasketServiceIntegrationTests {

    @Autowired
    private BasketService basketService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    private UserInfo userInfoOne;
    private UserInfo userInfoTwo;

    private OrderRequestDto orderRequestDto;

    @BeforeEach
    void beforeEach() {
        LocalDate localDate = LocalDate.now();
        orderRequestDto = new OrderRequestDto("type2", "name", "phone",
                "deliveryaddress", localDate, 1555L, 1600L);
        userInfoOne = new UserInfo(1L, "user", "user13@mail.ru", "ROLE_USER");
        userInfoTwo = new UserInfo(2L, "SomeName", "some11@mail.ru", "ROLE_USER");
    }

    @Test
    void shouldPostOrderWhenOk() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        orderService.postOrder(orderRequestDto, 1L);
        if (orderRepository.findById(1L).isPresent()) {
            assertThat(orderRepository.findById(1L).get().getId(), equalTo(1L));
            assertThat(orderRepository.findById(1L).get().getFinalPrice(), equalTo(1600L));
        }
        assertThrows(DataNotFoundException.class, () -> basketService.getBasket(1L));
        assertThat(orderService.getOrder(1L, userInfoOne.getId()).getId(), equalTo(1L));
        basketService.addModelToBasket(1L, 1L);
        orderService.postOrder(orderRequestDto, 1L);
        if (orderRepository.findById(2L).isPresent()) {
            assertThat(orderRepository.findById(2L).get().getId(), equalTo(2L));
            assertThat(orderRepository.findById(2L).get().getFinalPrice(), equalTo(1600L));
        }
        assertThat(orderService.getOrder(userInfoOne.getId(), 2L).getId(), equalTo(2L));
        assertThat(orderService.getOrder(userInfoOne.getId(), 1L).getId(), equalTo(1L));
    }

    @Test
    void shouldAddModelsToBasketWithUserAndModelOk() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        assertThat(basketService.getBasket(userInfoOne.getId()).getId(), equalTo(1L));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().size(), equalTo(2));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(0).getModelShortDto().getName(), equalTo("ModelOne"));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(0).getCount(), equalTo(1));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(1).getCount(), equalTo(1));
    }

    @Test
    void shouldAddCountToModelsInBasketsWithUsersAndModelsOk() {
        userInfoService.addUser(userInfoOne);
        userInfoService.addUser(userInfoTwo);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.plusCountModelSet(1L, 1L);
        basketService.plusCountModelSet(2L, 1L);
        basketService.plusCountModelSet(2L, 1L);
        basketService.addModelToBasket(4L, 2L);
        basketService.addModelToBasket(2L, 2L);
        basketService.plusCountModelSet(3L, 2L);
        basketService.plusCountModelSet(4L, 2L);
        basketService.plusCountModelSet(4L, 2L);
        basketService.plusCountModelSet(4L, 2L);
        assertThat(basketService.getBasket(userInfoOne.getId()).getId(), equalTo(1L));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().size(), equalTo(2));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(0).getCount(), equalTo(2));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(1).getCount(), equalTo(3));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().get(0).getCount(), equalTo(2));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().get(1).getCount(), equalTo(4));
    }

    @Test
    void shouldSetCountPlus1WhenAddSameModelToBasketWithUserAndModelOk() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(2L, 1L);
        assertThat(basketService.getBasket(userInfoOne.getId()).getId(), equalTo(1L));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().size(), equalTo(1));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(0).getCount(), equalTo(2));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(0).getModelShortDto().getName(), equalTo("ModelTwo"));
    }

    @Test
    void shouldAddSameModelToDifferentBasketsWithUserAndModelOk() {
        userInfoService.addUser(userInfoOne);
        userInfoService.addUser(userInfoTwo);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(1L, 2L);
        assertThat(basketService.getBasket(userInfoOne.getId()).getId(), equalTo(1L));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(0).getModelShortDto().getName(), equalTo("ModelOne"));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getId(), equalTo(2L));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().get(0).getModelShortDto().getName(), equalTo("ModelOne"));
    }

    @Test
    void shouldSetCountPlus1WhenAddSameModelToDifferentBasketsTwiceWithUserAndModelOk() {
        userInfoService.addUser(userInfoOne);
        userInfoService.addUser(userInfoTwo);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(1L, 2L);
        basketService.addModelToBasket(4L, 2L);
        basketService.addModelToBasket(1L, 2L);
        assertThat(basketService.getBasket(userInfoOne.getId()).getId(), equalTo(1L));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().size(), equalTo(2));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(0).getModelShortDto().getName(), equalTo("ModelOne"));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(0).getCount(), equalTo(2));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getId(), equalTo(2L));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().size(), equalTo(2));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().get(0).getModelShortDto().getName(), equalTo("ModelOne"));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().get(0).getCount(), equalTo(2));
    }

    @Test
    void shouldDeleteModelFromBasketWhenUserAndModelOk() {
        userInfoService.addUser(userInfoOne);
        userInfoService.addUser(userInfoTwo);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        basketService.removeModelSetFromBasket(1L, 1L);
        basketService.addModelToBasket(1L, 2L);
        basketService.addModelToBasket(4L, 2L);
        basketService.addModelToBasket(1L, 2L);
        basketService.removeModelSetFromBasket(4L, 2L);
        assertThat(basketService.getBasket(userInfoOne.getId()).getId(), equalTo(1L));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().size(), equalTo(1));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getId(), equalTo(2L));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().size(), equalTo(1));
    }

    @Test
    void shouldReturnActualPriceSumForGetBasketWhenUserAndModelOk() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(3L, 1L);
        BasketGetResponseDto basketGetResponseDto = basketService.getBasket(1L);
        assertThat(basketGetResponseDto.getActualPriceSum(), equalTo(500L));
        assertThat(basketGetResponseDto.getOldPriceSum(), equalTo(0L));
    }

    @Test
    void shouldThrowDataNotFoundExceptionForAddModelToBasketWhenModelIdNotFound() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        assertThrows(DataNotFoundException.class, () -> basketService.addModelToBasket(10L, 1L));
    }

    @Test
    void shouldThrowDataNotFoundExceptionForGetBasketWhenUserIdNotFound() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        assertThrows(DataNotFoundException.class, () -> basketService.getBasket(10L));
    }

    @Test
    void shouldThrowDataNotFoundExceptionForAddModelToBasketWhenUserIdNotFound() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        assertThrows(DataNotFoundException.class, () -> basketService.addModelToBasket(1L, 13L));
    }

    @Test
    void shouldThrowDataNotFoundExceptionForAddModelToBasketWhenModeIdNotFound() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        assertThrows(DataNotFoundException.class, () -> basketService.addModelToBasket(12L, 1L));
    }

    @Test
    void shouldThrowDataNotFoundExceptionForRemoveModelFromBasketWhenModeIdNotFound() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        assertThrows(DataNotFoundException.class, () -> basketService.removeModelSetFromBasket(12L, 1L));
    }

    @Test
    void shouldThrowDataNotFoundExceptionForRemoveModelFromBasketWhenUserIdNotFound() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        assertThrows(DataNotFoundException.class, () -> basketService.removeModelSetFromBasket(1L, 2L));
    }

    @Test
    void shouldReduceCountToModelsInBasketsWithUsersAndModelsOk() {
        userInfoService.addUser(userInfoOne);
        userInfoService.addUser(userInfoTwo);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.plusCountModelSet(1L, 1L);
        basketService.plusCountModelSet(2L, 1L);
        basketService.plusCountModelSet(2L, 1L);
        basketService.addModelToBasket(4L, 2L);
        basketService.addModelToBasket(2L, 2L);
        basketService.plusCountModelSet(3L, 2L);
        basketService.plusCountModelSet(4L, 2L);
        basketService.plusCountModelSet(4L, 2L);
        basketService.plusCountModelSet(4L, 2L);
        assertThat(basketService.getBasket(userInfoOne.getId()).getId(), equalTo(1L));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().size(), equalTo(2));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(0).getCount(), equalTo(2));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(1).getCount(), equalTo(3));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().get(0).getCount(), equalTo(2));
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().get(1).getCount(), equalTo(4));
        basketService.minusCountModelSet(4L, 2L);
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().get(1).getCount(), equalTo(3));
        basketService.minusCountModelSet(4L, 2L);
        assertThat(basketService.getBasket(userInfoTwo.getId()).getModelSetResponseDtos().get(1).getCount(), equalTo(2));
        basketService.minusCountModelSet(1L, 1L);
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().get(0).getCount(), equalTo(1));
    }

    @Test
    void shouldRemoveModelSetAndReturnEmptyModelSetListWhenReduceCountFromOne() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        assertThat(basketService.getBasket(userInfoOne.getId()).getId(), equalTo(1L));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().size(), equalTo(1));
        basketService.minusCountModelSet(1L, 1L);
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos().size(), equalTo(0));
        assertThat(basketService.getBasket(userInfoOne.getId()).getModelSetResponseDtos(), equalTo(new ArrayList<>()));
    }

    @Test
    void shouldThrowDataNotFoundExceptionForMinusCountForModelSetWhenModelSetIdNotFound() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        assertThrows(DataNotFoundException.class, () -> basketService.minusCountModelSet(3L, 1L));
    }

    @Test
    void shouldThrowDataNotFoundExceptionForMinusCountForModelSetWhenBasketForUserIdNotFound() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        assertThrows(DataNotFoundException.class, () -> basketService.minusCountModelSet(1L, 3L));
    }

    @Test
    void shouldThrowDataNotFoundExceptionForPlusCountForModelSetWhenModelSetIdNotFound() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        assertThrows(DataNotFoundException.class, () -> basketService.plusCountModelSet(3L, 1L));
    }

    @Test
    void shouldThrowDataNotFoundExceptionForPlusCountForModelSetWhenBasketForUserIdNotFound() {
        userInfoService.addUser(userInfoOne);
        basketService.addModelToBasket(1L, 1L);
        basketService.addModelToBasket(2L, 1L);
        basketService.addModelToBasket(1L, 1L);
        assertThrows(DataNotFoundException.class, () -> basketService.plusCountModelSet(1L, 3L));
    }
}