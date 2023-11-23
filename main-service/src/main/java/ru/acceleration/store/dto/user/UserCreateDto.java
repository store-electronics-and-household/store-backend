package ru.acceleration.store.dto.user;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserCreateDto {

    @NotBlank
    @Size(max = 250)
    private String username;

    @Email
    @NotBlank
    @Size(max = 250)
    private String email;

    @NotBlank
    private String password;
}
