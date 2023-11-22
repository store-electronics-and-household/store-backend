package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.abstraction.PatchMap;
import ru.acceleration.store.model.Address;
import ru.acceleration.store.model.User;
import ru.acceleration.store.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;

    private final UserService userService;

    private final PatchMap<Address> patchMap;

    public Address save(Address address, Long userId) {
        log.info("AddressService save address {}, userId {}", address, userId);
        User user = userService.getById(userId);
        List<Address> addressList = new ArrayList<>();
        addressList = user.getAdresses();
        addressList.add(address);
        user.setAdresses(addressList);
        userService.save(user);
        return addressRepository.save(address);
    }
}
