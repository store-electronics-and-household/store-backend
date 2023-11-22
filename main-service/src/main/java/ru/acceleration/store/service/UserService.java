package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.abstraction.PatchMap;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.User;
import ru.acceleration.store.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PatchMap<User> patchMap;

    public User save(User user) {
        log.info("UserService save user {}", user);
        return userRepository.save(user);
    }

    public List<User> saveList(List<User> users) {
        log.info("UserService saveList users {}", users);
        return userRepository.saveAll(users);
    }

    public void delete(Long id) {
        log.info("UserService delete id {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Пользователя с id=" + id + " нет"));
        userRepository.delete(user);
    }

    public User getById(Long id) {
        log.info("UserService getUserName id {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Пользователя с id=" + id + " нет"));
        return user;
    }

    public void putUserName(Long id, User user) {
        log.info("UserService putUserName id={}, user={}", id, user);
        User userBD = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Пользователя с id=" + id + " нет"));
        if (id.equals(userBD.getId())) {
            userBD = patchMap.patchObject(userBD, user);
            userRepository.save(userBD);
        }
    }
}
