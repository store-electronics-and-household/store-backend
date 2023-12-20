package ru.acceleration.store.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.abstraction.PatchMap;
import ru.acceleration.store.exceptions.BadRequestException;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.User;
import ru.acceleration.store.repository.UserRepository;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.service.model.ModelService;

import java.util.Set;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ModelService modelService;

    private final PatchMap<User> patchMap;

    @Override
    public User create(User user, UserInfo userInfo) {
        if (userRepository.findByUserInfo(userInfo).isPresent()) {
            throw new BadRequestException("Пользователь с почтой " + userInfo.getEmail() + " уже создан");
        }
        user.setUserInfo(userInfo);
        return userRepository.save(user);
    }

    @Override
    public User patch(User user, UserInfo userInfo) {
        User userOld = userRepository.findByUserInfo(userInfo).orElseThrow(() -> new DataNotFoundException("Пользователь с почтой " + userInfo.getEmail() + " не существует"));
        user = patchMap.patchObject(user, userOld);
        return userRepository.save(user);

    }

    @Override
    public void delete(UserInfo userInfo) {
        User userOld = userRepository.findByUserInfo(userInfo).orElseThrow(() -> new DataNotFoundException("Пользователь с почтой " + userInfo.getEmail() + " не существует"));
        userRepository.delete(userOld);
    }

    @Override
    public User getUser(UserInfo userInfo) {
        User user = userRepository.findByUserInfo(userInfo).orElseThrow(() -> new DataNotFoundException("Пользователь с почтой " + userInfo.getEmail() + " не существует"));
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return getExistingById(id);
    }

    @Override
    public void addFavoriteModel(Long modelId, Long userInfoId) {
        User user = userRepository.findByUserInfoId(userInfoId).orElseThrow(() -> new DataNotFoundException("Пользователь с UserInfoId " + userInfoId + " не существует"));;
        Model model = modelService.getExistingModel(modelId);
        user.getModels().add(model);
        userRepository.save(user);
    }

    @Override
    public void deleteFavoriteModel(Long modelId, Long userInfoId) {
        User user = userRepository.findByUserInfoId(userInfoId).orElseThrow(() -> new DataNotFoundException("Пользователь с UserInfoId " + userInfoId + " не существует"));;
        Model model = modelService.getExistingModel(modelId);
        user.getModels().remove(model);
        userRepository.save(user);
    }

    @Override
    public Set<Model> getAllFavorite(Long userInfoId) {
        User user = userRepository.findByUserInfoId(userInfoId).orElseThrow(() -> new DataNotFoundException("Пользователь с UserInfoId " + userInfoId + " не существует"));;
        return user.getModels();
    }

    private User getExistingById(Long Id) {
        return userRepository.findById(Id).orElseThrow(() -> {
            throw new DataNotFoundException(String.format("User with id=%d was not found", Id));
        });
    }
}
