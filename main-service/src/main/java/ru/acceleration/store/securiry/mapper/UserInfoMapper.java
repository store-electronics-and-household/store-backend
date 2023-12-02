package ru.acceleration.store.securiry.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.acceleration.store.securiry.dto.UserInfoRequestDto;
import ru.acceleration.store.securiry.dto.UserInfoResponseDto;
import ru.acceleration.store.securiry.model.UserInfo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper {
    // UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    UserInfo userRequestDtoToUserInfo(UserInfoRequestDto userDto);

    UserInfoResponseDto userInfoToUserResponseDto(UserInfo user);
}
