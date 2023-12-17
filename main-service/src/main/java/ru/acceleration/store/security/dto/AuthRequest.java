package ru.acceleration.store.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 6, message = "Должно быть не меньше 6 символов")
    private String password;
}
