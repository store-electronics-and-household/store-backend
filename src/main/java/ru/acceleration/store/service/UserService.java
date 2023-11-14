package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.model.User;
import ru.acceleration.store.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        log.info("UserService save user {}", user);
        return userRepository.save(user);
    }

    public List<User> saveList(List<User> users) {
        log.info("UserService saveList users {}", users);
        return userRepository.saveAll(users);
    }

    public void delete(String username) {
        log.info("UserService delete username {}", username);
       // userRepository.deleteByUserName(username);
        User user = userRepository.findByUserName(username).orElseThrow(() -> new DataNotFoundException("Пользователя с username=" + username + " нет"));
        userRepository.delete(user);
    }

    public User getUserName(String username) {
        log.info("UserService getUserName username {}", username);
        User user = userRepository.findByUserName(username).orElseThrow(() -> new DataNotFoundException("Пользователя с username=" + username + " нет"));
        return user;
    }

    public void putUserName(String username, User user) {
        log.info("UserService putUserName username={}, user={}", username, user);
        User userBD = userRepository.findByUserName(username).orElseThrow(() -> new DataNotFoundException("Пользователя с username=" + username + " нет"));
        if (username.equals(userBD.getUserName())) {
            user.setUserId(userBD.getUserId());
            userRepository.save(user);
        }
    }

    public void getLogin(String username, String password) {
        log.info("UserService getLogin username={}, password={}", username, password);
        User user = userRepository.findByUserNameAndPassword(username, password).orElseThrow(() -> new DataNotFoundException("Пользователя с username=" + username + " password=" + password + " нет"));
    }
}
