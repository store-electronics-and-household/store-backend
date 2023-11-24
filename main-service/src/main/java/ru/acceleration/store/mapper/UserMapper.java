package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.user.UserRequestDto;
import ru.acceleration.store.dto.user.UserResponseDto;
import ru.acceleration.store.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponseDto toUserDto(UserRequestDto userRequestDto);

    User toUser(UserResponseDto userResponseDto);

    UserResponseDto toUserDto(User user);
}
