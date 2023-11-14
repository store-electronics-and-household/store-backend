package ru.acceleration.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "address")
@Builder
public class Address extends AbstractPersistable<Long> {

    @Column(name = "town")
    String town;

    @Column(name = "street")
    String street;

    @Column(name = "house_number")
    String houseNumber;

    @Column(name = "flat_number")
    String flatNumber;

    @Column(name = "entrance_number")
    String entranceNumber;

    @Column(name = "floor_number")
    String floorNumber;

    @Column(name = "comment")
    String comment;
}
