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
public class OrderResponse {
    @EqualsAndHashCode.Exclude
    Long orderId;
    Long userId;
    Long productId;
    LocalDateTime created;
    Integer sum;
    Long salesId;
    String status;
}