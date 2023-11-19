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
public class PaymentResponse {
    @EqualsAndHashCode.Exclude
    Long paymentId;
    Long orderId;
    Long userId;
    Long bankCardId;
    String status;
    LocalDateTime created;
}