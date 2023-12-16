package ru.acceleration.store.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressUpdateRequestDto {

    private String name;

    private String city;

    private String street;

    private Long houseNumber;

    private Long flatNumber;

    private Long entranceNumber;

    private Long floorNumber;

    private String comment;
}
