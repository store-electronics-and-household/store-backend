package ru.acceleration.store.security.dto;

import jakarta.validation.constraints.*;
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
    @Size(min = 6, message = "Должно быть не меньше 6 символов")
    String password;
}
