package ru.acceleration.store.model.dto;

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

    String userName;

    String firstName;

    String lastName;

    String email;

    String password;

    String phone;

    Long userStatus;
}
