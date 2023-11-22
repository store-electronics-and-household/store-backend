package ru.acceleration.store.mapper;

import org.mapstruct.Mapper;
import ru.acceleration.store.model.User;
import ru.acceleration.store.dto.UserRequest;
import ru.acceleration.store.dto.UserResponse;

@Mapper
public interface UserMapper {

    User userRequestToUser(UserRequest userRequest);

    UserResponse userToUserResponse(User user);

}
