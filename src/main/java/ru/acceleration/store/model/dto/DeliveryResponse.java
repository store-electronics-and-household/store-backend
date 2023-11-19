package ru.acceleration.store.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryResponse {
    @EqualsAndHashCode.Exclude
    Long deliveryId;
    Long orderId;
    Long userId;
    String status;
    LocalDateTime created;
}