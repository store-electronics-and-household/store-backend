package ru.acceleration.store.service.user;

import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.user.UserRequestDto;
import ru.acceleration.store.dto.user.UserResponseDto;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.User;

import java.util.Set;

@Service
public interface UserService {

    UserResponseDto postUser(UserRequestDto userRequestDto);

    User getUserById(Long id);

    void addFavoriteModel(Long modelId, Long userId);

    void deleteFavoriteModel(Long modelId, Long userId);

    Set<Model> getAllFavorite(Long userId);
}
