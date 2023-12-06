package ru.acceleration.store.securiry.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoRequestDto {

    @Email
    @NotBlank
    @NotNull
    String email;

    @NotBlank
    @NotNull
    String password;

    String roles;
}
