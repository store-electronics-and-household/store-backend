package ru.acceleration.store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
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
