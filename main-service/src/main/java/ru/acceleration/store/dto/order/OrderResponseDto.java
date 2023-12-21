package ru.acceleration.store.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.dto.basket.BasketResponseDto;
import ru.acceleration.store.model.enums.DeliveryType;
import ru.acceleration.store.model.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private Long id;

    private BasketResponseDto orderBasket;

    private Long paymentId;

    private Long addressId;

    private LocalDateTime created;

    private Long finalPrice;

    private OrderStatus orderStatus;

    private DeliveryType deliveryType;

    private String name;

    private String phone;

    private String deliveryAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    private Long deliveryPrice;
}
