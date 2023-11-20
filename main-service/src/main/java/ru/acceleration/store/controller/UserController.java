package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.mapper.UserMapper;
import ru.acceleration.store.model.User;
import ru.acceleration.store.model.dto.UserRequest;
import ru.acceleration.store.model.dto.UserResponce;
import ru.acceleration.store.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponce postUser(@RequestBody @Valid UserRequest dto) {
        log.info("UserController postUser dto {}", dto);
        User user = UserMapper.toUser(dto);
        user = userService.save(user);
        return UserMapper.toUserResponce(user);
    }

    @PostMapping(value = "/createWithList")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserResponce> postUserList(@RequestBody @Valid List<UserRequest> dto) {
        log.info("UserController postUserList dto {}", dto);
        List<User> userList = dto.stream()
                .map(UserMapper::toUser)
                .collect(Collectors.toList());
        userList = userService.saveList(userList);
        return userList.stream()
                .map(UserMapper::toUserResponce)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        log.info("UserController deleteUser  id={}", id);
        userService.delete(id);
    }

    @GetMapping(value = "/{username}")
    public UserResponce getUserName(@PathVariable Long id) {
        log.info("UserController getUserName  id={}", id);
        User user = userService.getUserName(id);
        return UserMapper.toUserResponce(user);
    }

    @PutMapping(value = "/{username}")
    public void putUserName(@PathVariable Long id, @RequestBody @Valid UserRequest dto) {
        log.info("UserController putUserName id={}, dto={}", id, dto);
        User user = UserMapper.toUser(dto);
        userService.putUserName(id, user);
    }
}
