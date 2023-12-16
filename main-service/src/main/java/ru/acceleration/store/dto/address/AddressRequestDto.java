package ru.acceleration.store.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotNull
    private Long houseNumber;

    @NotNull
    private Long flatNumber;

    @NotNull
    private Long entranceNumber;

    @NotNull
    private Long floorNumber;

    private String comment;
}
