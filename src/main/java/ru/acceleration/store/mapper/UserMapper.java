package ru.acceleration.store.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.User;
import ru.acceleration.store.model.dto.UserRequest;
import ru.acceleration.store.model.dto.UserResponce;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

    public static UserResponce toUserResponce(User user) {
        return UserResponce.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .userStatus(user.getUserStatus())
                .build();
    }

    public static User toUser(UserRequest dto) {
        return User.builder()
                .userName(dto.getUserName())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .userStatus(dto.getUserStatus())
                .build();
    }
}
