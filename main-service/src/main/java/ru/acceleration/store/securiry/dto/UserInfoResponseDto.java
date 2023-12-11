package ru.acceleration.store.securiry.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoResponseDto {

    long id;
    String email;
    String password;
    String roles;
}
