package ru.acceleration.store.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.security.dto.UserInfoRequestDto;
import ru.acceleration.store.security.dto.UserInfoResponseDto;
import ru.acceleration.store.security.model.UserInfo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper {
    // UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    UserInfo userRequestDtoToUserInfo(UserInfoRequestDto userDto);

    UserInfoResponseDto userInfoToUserResponseDto(UserInfo user);
}
