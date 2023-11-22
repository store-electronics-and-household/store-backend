package ru.acceleration.store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AddressRequest {

    String town;

    String street;

    String houseNumber;

    String flatNumber;

    String entranceNumber;

    String floorNumber;

    String comment;
}
