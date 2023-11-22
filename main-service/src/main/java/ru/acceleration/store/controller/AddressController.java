package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.AddressRequest;
import ru.acceleration.store.dto.AddressResponse;
import ru.acceleration.store.mapper.AddressMapper;
import ru.acceleration.store.model.Address;
import ru.acceleration.store.service.AddressService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/address")
@Slf4j
public class AddressController {

    private final AddressService addressService;

    private final AddressMapper addressMapper;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse postAddress(@RequestBody @Valid AddressRequest dto, @RequestParam Long userId) {
        log.info("AddressController postAddress dto {}, userId {}", dto, userId);
        Address address = addressMapper.addressRequestToAddress(dto);
        address = addressService.save(address, userId);
        return addressMapper.addressToAddressResponse(address);
    }
}
