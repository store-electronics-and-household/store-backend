package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import ru.acceleration.store.dto.UserCreateDto;
import ru.acceleration.store.model.Authority;


@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    Authority toAuthority(UserCreateDto userCreateDto);
}
