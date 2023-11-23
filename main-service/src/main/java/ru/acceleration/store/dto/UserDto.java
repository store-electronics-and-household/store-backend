package ru.acceleration.store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDto {

    Long id;

    String username;

    String email;

    String password;

    @Builder.Default
    Boolean enabled = true;

    String firstName;

    String lastName;

    String phone;

    Long addressId;
}