package ru.acceleration.store.service.address;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.address.AddressRequestDto;
import ru.acceleration.store.dto.address.AddressResponseDto;
import ru.acceleration.store.dto.address.AddressUpdateRequestDto;

@Service
public interface AddressService {

    AddressResponseDto postAddress(AddressRequestDto addressRequestDto, Long userId);

    AddressResponseDto patchAddress(Long addressId, AddressUpdateRequestDto addressUpdateRequestDto, Long userId);

    void deleteAddress(Long addressId, Long userId);
}
