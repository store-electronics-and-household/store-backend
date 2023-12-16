package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.address.AddressRequestDto;
import ru.acceleration.store.dto.address.AddressResponseDto;
import ru.acceleration.store.dto.address.AddressUpdateRequestDto;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.service.UserInfoService;
import ru.acceleration.store.service.address.AddressService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {

    private final UserInfoService userInfoService;
    private final AddressService addressService;

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<AddressResponseDto> postAddress(@Valid @RequestBody AddressRequestDto addressRequestDto,
                                                          Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        return ResponseEntity.status(201).body(addressService.postAddress(addressRequestDto, userInfo.getId()));
    }

    @PatchMapping("/{addressId}/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<AddressResponseDto> patchAddress(@PathVariable Long addressId,
                                                           @Valid @RequestBody AddressUpdateRequestDto addressUpdateRequestDto,
                                                           Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        return ResponseEntity.ok().body(addressService.patchAddress(addressId, addressUpdateRequestDto, userInfo.getId()));
    }

    @DeleteMapping("/{addressId}/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId, Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        addressService.deleteAddress(addressId, userInfo.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
