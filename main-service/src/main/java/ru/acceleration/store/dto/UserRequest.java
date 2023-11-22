package ru.acceleration.store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequest {

    String name;

    String lastName;

    String telephoneNumber;

    String login;

    String password;

    String registrationStatus;

    String agreement;
}
