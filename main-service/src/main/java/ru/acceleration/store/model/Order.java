package ru.acceleration.store.model;


import jakarta.persistence.*;
import lombok.*;
import ru.acceleration.store.model.enums.DeliveryType;
import ru.acceleration.store.model.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Builder
@ToString(exclude = {"basket"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address userAddress;

    @Column(name = "created")
    @Builder.Default
    private LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

    @Column(name = "delivery_type")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String deliveryAddress;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "delivery_price")
    private Long deliveryPrice;

    @Column(name = "final_price")
    private Long finalPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus orderStatus = OrderStatus.STATUS_OK;
}
