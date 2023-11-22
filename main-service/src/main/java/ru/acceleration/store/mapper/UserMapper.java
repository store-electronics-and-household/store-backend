package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import ru.acceleration.store.dto.UserCreateDto;
import ru.acceleration.store.dto.UserDto;
import ru.acceleration.store.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(UserCreateDto userCreateDto);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
