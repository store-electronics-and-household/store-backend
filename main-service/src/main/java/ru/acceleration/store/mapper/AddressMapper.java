package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.acceleration.store.dto.address.AddressRequestDto;
import ru.acceleration.store.dto.address.AddressResponseDto;
import ru.acceleration.store.model.Address;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AddressMapper {

    Address toAddress(AddressRequestDto addressRequestDto);

    @Mapping(target = "userId", source = "user.id")
    AddressResponseDto toAddressResponseDto(Address address);
}
