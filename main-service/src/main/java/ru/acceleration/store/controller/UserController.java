package ru.acceleration.store.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.UserCreateDto;
import ru.acceleration.store.dto.UserDto;
import ru.acceleration.store.service.UserService;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> postUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        log.info("POST: /user");
        return ResponseEntity.status(201).body(userService.postUser(userCreateDto));
    }

}
