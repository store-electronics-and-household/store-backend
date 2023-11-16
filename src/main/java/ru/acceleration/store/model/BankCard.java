package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "bank_card")
@Builder
public class BankCard extends AbstractPersistable<Long> {

    @Column(name = "name_oner")
    String nameOner;

    @Column(name = "last_name_oner")
    String lastNameOner;

    @Column(name = "action_period")
    Date actionPeriod;
}