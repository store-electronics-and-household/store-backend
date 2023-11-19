package ru.acceleration.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductInOrderResponse {
    @EqualsAndHashCode.Exclude
    Long productInOrderId;
    Long productId;
    Long orderId;
}