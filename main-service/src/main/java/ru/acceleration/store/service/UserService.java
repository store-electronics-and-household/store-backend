package ru.acceleration.store.service;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.UserCreateDto;
import ru.acceleration.store.dto.UserDto;

@Service
public interface UserService {

    UserDto postUser(UserCreateDto userCreateDto);
}
