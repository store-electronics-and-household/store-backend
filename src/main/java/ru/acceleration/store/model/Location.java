package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "LOCATION")
public class Location {

    // Локация

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Идентификатор локации

    private String country;  // страна
    private String city;  // город
    private String street;  // улица
    private String house;  // дом
    private String frame;  // корпус
    private String entrance;  // подьезд
    private String floor;  // этаж
    private String apartment;  // квартира
    private float lat;   // Широта
    private float lon;   // Долгота

}
