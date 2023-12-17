package ru.acceleration.store.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    @Size(max = 15)
    String phone;
}
