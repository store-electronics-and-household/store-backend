package ru.acceleration.store.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "ORDER")
public class Order {

    // Заказ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Идентификатор заказа

    @ManyToOne
    private User buyer;   // Покупатель

    @ManyToOne
    private Product product;   // купленный товар

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;   // время оформления заказа

    private String status;   // статус заказа (в дальгейшем Enum или класс)

    // и др поля по надобности

}
