package ru.acceleration.store.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.Basket;
import ru.acceleration.store.model.enums.DeliveryType;
import ru.acceleration.store.model.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private Long id;

    private Basket orderBasket;

    private Long paymentId;

    private Long addressId;

    private LocalDateTime created;

    private Long finalPrice;

    private OrderStatus orderStatus;

    private DeliveryType deliveryType;

    private String name;

    private String phone;

    private String deliveryAddress;

    private LocalDate deliveryDate;

    private Long deliveryPrice;
}
