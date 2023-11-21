package ru.acceleration.store.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.acceleration.store.model.User;
import ru.acceleration.store.dto.UserRequest;
import ru.acceleration.store.dto.UserResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

    public static UserResponse toUserResponce(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .telephoneNumber(user.getTelephoneNumber())
                .registrationStatus(user.getRegistrationStatus())
                .build();
    }

    public static User toUser(UserRequest dto) {
        return User.builder()
                .name(dto.getName())
                .lastName(dto.getLastName())
                .telephoneNumber(dto.getTelephoneNumber())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .registrationStatus(dto.getRegistrationStatus())
                .agreement(dto.getAgreement())
                .build();
    }

    public static User userToUser(User user) {
        user.setName(user.getName());
        user.setLastName(user.getLastName());
        user.setTelephoneNumber(user.getTelephoneNumber());
        user.setLogin(user.getLogin());
        user.setPassword(user.getPassword());
        user.setRegistrationStatus(user.getRegistrationStatus());
        user.setAgreement(user.getAgreement());
        return user;
    }
}
