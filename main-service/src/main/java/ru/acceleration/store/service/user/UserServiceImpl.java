package ru.acceleration.store.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.user.UserRequestDto;
import ru.acceleration.store.dto.user.UserResponseDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.AuthorityMapper;
import ru.acceleration.store.mapper.UserMapper;

import ru.acceleration.store.model.Authority;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.Role;
import ru.acceleration.store.model.User;
import ru.acceleration.store.repository.AuthorityRepo;
import ru.acceleration.store.repository.UserRepository;
import ru.acceleration.store.service.model.ModelService;

import java.util.Set;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthorityRepo authorityRepo;
    private final UserMapper userMapper;
    private final AuthorityMapper authorityMapper;
    private final ModelService modelService;


    @Override
    public UserResponseDto postUser(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userMapper.toUserDto(userRequestDto);
        User user = userRepository.save(userMapper.toUser(userResponseDto));
        Authority authority = authorityMapper.toAuthority(userRequestDto);
        authority.setRole(Role.ROLE_USER);
        authorityRepo.save(authority);
        return userMapper.toUserDto(user);
    }

    @Override
    public User getUserById(Long id) {
        return getExistingById(id);
    }

    @Override
    public void addFavoriteModel(Long modelId, Long userId) {
        User user = getExistingById(userId);
        Model model = modelService.getExistingModel(modelId);
        user.getModels().add(model);
        userRepository.save(user);
    }

    @Override
    public void deleteFavoriteModel(Long modelId, Long userId) {
        User user = getExistingById(userId);
        Model model = modelService.getExistingModel(modelId);
        user.getModels().remove(model);
        userRepository.save(user);
    }

    @Override
    public Set<Model> getAllFavorite(Long userId) {
        User user = getExistingById(userId);
        return user.getModels();
    }

    private User getExistingById(Long Id) {
        return userRepository.findById(Id).orElseThrow(() -> {
            throw new DataNotFoundException(String.format("User with id=%d was not found", Id));
        });
    }
}
