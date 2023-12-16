package ru.acceleration.store.service.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.address.AddressRequestDto;
import ru.acceleration.store.dto.address.AddressResponseDto;
import ru.acceleration.store.dto.address.AddressUpdateRequestDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.AddressMapper;
import ru.acceleration.store.model.Address;
import ru.acceleration.store.model.User;
import ru.acceleration.store.repository.AddressRepository;
import ru.acceleration.store.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserRepository userRepository;


    @Override
    public AddressResponseDto postAddress(AddressRequestDto addressRequestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new DataNotFoundException("user with id: " + userId + " not found"));
        Address address = addressMapper.toAddress(addressRequestDto);
        address.setUser(user);
        addressRepository.save(address);
        return addressMapper.toAddressResponseDto(address);
    }

    @Override
    public AddressResponseDto patchAddress(Long addressId, AddressUpdateRequestDto addressUpdateRequestDto, Long userId) {
        Address oldAddress = addressRepository.findByIdAndUserId(addressId, userId).orElseThrow(()
                -> new DataNotFoundException("address with id: " + addressId +
                " for user with id: " + userId + " not found"));
        Address newAddress = addressParametersUpdate(oldAddress, addressUpdateRequestDto);
        addressRepository.save(newAddress);
        return addressMapper.toAddressResponseDto(newAddress);
    }

    @Override
    public void deleteAddress(Long addressId, Long userId) {
        Address address = addressRepository.findByIdAndUserId(addressId, userId).orElseThrow(()
                -> new DataNotFoundException("address with id: " + addressId +
                " for user with id: " + userId + " not found"));
        addressRepository.deleteById(address.getId());
    }

    private Address addressParametersUpdate(Address oldAddress, AddressUpdateRequestDto addressUpdateRequestDto) {
        if (addressUpdateRequestDto.getName() != null) {
            if (!addressUpdateRequestDto.getName().isBlank()) {
                oldAddress.setName(addressUpdateRequestDto.getName());
            }
        }
        if (addressUpdateRequestDto.getCity() != null) {
            if (!addressUpdateRequestDto.getCity().isBlank()) {
                oldAddress.setCity(addressUpdateRequestDto.getCity());
            }
        }
        if (addressUpdateRequestDto.getStreet() != null) {
            if (!addressUpdateRequestDto.getStreet().isBlank()) {
                oldAddress.setStreet(addressUpdateRequestDto.getStreet());
            }
        }
        if (addressUpdateRequestDto.getHouseNumber() != null) {
            oldAddress.setHouseNumber(addressUpdateRequestDto.getHouseNumber());
        }
        if (addressUpdateRequestDto.getFlatNumber() != null) {
                oldAddress.setFlatNumber(addressUpdateRequestDto.getFlatNumber());
        }
        if (addressUpdateRequestDto.getEntranceNumber() != null) {
                oldAddress.setEntranceNumber(addressUpdateRequestDto.getEntranceNumber());
        }
        if (addressUpdateRequestDto.getFloorNumber() != null) {
                oldAddress.setFloorNumber(addressUpdateRequestDto.getFloorNumber());
        }
        if (addressUpdateRequestDto.getComment() != null) {
            if (!addressUpdateRequestDto.getComment().isBlank()) {
                oldAddress.setComment(addressUpdateRequestDto.getComment());
            }
        }
        return oldAddress;
    }
}
