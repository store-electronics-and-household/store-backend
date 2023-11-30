package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

   // UserResponseDto toUserDto(UserRequestDto userRequestDto);

 //   User toUser(UserResponseDto userResponseDto);

 //   UserResponseDto toUserDto(User user);
}
