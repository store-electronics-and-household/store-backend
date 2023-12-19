package ru.acceleration.store.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseCheckDto {

    Long id;

    String firstName;

    String email;
}
