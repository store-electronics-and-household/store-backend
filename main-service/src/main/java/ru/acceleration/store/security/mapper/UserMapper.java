package ru.acceleration.store.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.acceleration.store.security.dto.UserDto;
import ru.acceleration.store.security.dto.UserResponseDto;
import ru.acceleration.store.security.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);

    UserResponseDto userToUserResponseDto(User user);
}
