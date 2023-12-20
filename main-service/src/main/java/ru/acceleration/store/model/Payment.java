package ru.acceleration.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.enums.PaymentStatus;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "completed")
    private LocalDateTime completed;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull
    @Builder.Default
    private PaymentStatus paymentStatus = PaymentStatus.STATUS_OK;
}
