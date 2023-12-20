package ru.acceleration.store.service.user;

import org.springframework.stereotype.Service;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.User;
import ru.acceleration.store.security.model.UserInfo;

import java.util.Set;

@Service
public interface UserService {

//    UserResponseDto postUser(UserRequestDto userRequestDto);

    User getUserById(Long id);

    User create(User user, UserInfo userInfo);

    User patch(User user, UserInfo userInfo);

    void delete(UserInfo userInfo);

    User getUser(UserInfo userInfo);

    void addFavoriteModel(Long modelId, Long userInfoId);

    void deleteFavoriteModel(Long modelId, Long userInfoId);

    Set<Model> getAllFavorite(Long userInfoId);
}
