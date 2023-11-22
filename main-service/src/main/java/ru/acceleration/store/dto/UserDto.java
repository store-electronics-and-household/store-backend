package ru.acceleration.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDto {

    Long id;

    @NotBlank
    String username;

    @NotBlank
    String email;

    @NotBlank
    String password;

    @Builder.Default
    Boolean enabled = true;

    String fistName;

    String lastName;

    String phone;

    Long addressId;
}