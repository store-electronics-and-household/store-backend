package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.dto.user.UserResponseDto;
import ru.acceleration.store.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "addressResponseDtos", target = "addresses")
    @Mapping(source = "modelShortDtos", target = "models")
    User toUser(UserResponseDto userResponseDto);

    @Mapping(source = "addresses", target = "addressResponseDtos")
    @Mapping(source = "models", target = "modelShortDtos")
    UserResponseDto toUserResponseDto(User user);
}
