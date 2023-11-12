package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "bank_card")
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_card_id", nullable = false)
    Long id;

    @Column(name = "name_oner")
    String nameOner;

    @Column(name = "last_name_oner")
    String lastNameOner;

    @Column(name = "action_period")
    Date actionPeriod;
}
