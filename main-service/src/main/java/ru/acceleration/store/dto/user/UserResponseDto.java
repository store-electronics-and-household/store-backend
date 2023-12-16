package ru.acceleration.store.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class UserResponseDto {

    Long id;

    @Builder.Default
    Boolean enabled = true;

    String firstName;

    String lastName;

    String phone;

    Long addressId;

}