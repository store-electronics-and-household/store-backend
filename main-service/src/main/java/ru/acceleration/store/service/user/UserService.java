package ru.acceleration.store.service.user;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.user.UserRequestDto;
import ru.acceleration.store.dto.user.UserResponseDto;

@Service
public interface UserService {

    UserResponseDto postUser(UserRequestDto userRequestDto);
}
