package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.user.UserRequestDto;
import ru.acceleration.store.dto.user.UserRequestPatchDto;
import ru.acceleration.store.dto.user.UserResponseDto;
import ru.acceleration.store.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUser(UserRequestDto userRequestDto);

    User toUserFromPatch(UserRequestPatchDto userRequestPatchDto);

    @Mapping(source = "addresses", target = "addressResponseDtos")
    UserResponseDto toUserResponseDto(User user);
}
