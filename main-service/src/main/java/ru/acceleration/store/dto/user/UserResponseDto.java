package ru.acceleration.store.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.acceleration.store.dto.address.AddressResponseDto;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {

    Long id;

    String firstName;

    String lastName;

    String phone;

    List<AddressResponseDto> addressResponseDtos;
}