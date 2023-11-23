package ru.acceleration.store.service.user;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.user.UserCreateDto;
import ru.acceleration.store.dto.user.UserDto;

@Service
public interface UserService {

    UserDto postUser(UserCreateDto userCreateDto);
}
