package ru.acceleration.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.mapper.UserMapper;
import ru.acceleration.store.model.User;
import ru.acceleration.store.dto.UserRequest;
import ru.acceleration.store.dto.UserResponse;
import ru.acceleration.store.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse postUser(@RequestBody @Valid UserRequest dto) {
        log.info("UserController postUser dto {}", dto);
        User user = userMapper.userRequestToUser(dto);
        user = userService.save(user);
        return userMapper.userToUserResponse(user);
    }

    @PostMapping(value = "/createWithList")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserResponse> postUserList(@RequestBody @Valid List<UserRequest> dto) {
        log.info("UserController postUserList dto {}", dto);
        List<User> userList = dto.stream()
                .map(userMapper::userRequestToUser)
                .collect(Collectors.toList());
        userList = userService.saveList(userList);
        return userList.stream()
                .map(userMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestParam Long id) {
        log.info("UserController deleteUser  id={}", id);
        userService.delete(id);
    }

    @GetMapping(value = "/get")
    public UserResponse getById(@RequestParam Long id) {
        log.info("UserController getUserName  id={}", id);
        User user = userService.getById(id);
        return userMapper.userToUserResponse(user);
    }

    @PutMapping(value = "/put")
    public void putUser(@RequestParam Long id, @RequestBody @Valid UserRequest dto) {
        log.info("UserController putUserName id={}, dto={}", id, dto);
        User user = userMapper.userRequestToUser(dto);
        userService.putUserName(id, user);
    }
}
