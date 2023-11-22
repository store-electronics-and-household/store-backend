package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import ru.acceleration.store.dto.AddressRequest;
import ru.acceleration.store.dto.AddressResponse;
import ru.acceleration.store.model.Address;

@Mapper
public interface AddressMapper {

    Address addressRequestToAddress(AddressRequest addressRequest);

    AddressResponse addressToAddressResponse(Address address);
}
