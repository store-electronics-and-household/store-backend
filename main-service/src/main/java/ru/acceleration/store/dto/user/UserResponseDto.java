package ru.acceleration.store.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponseDto {

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