package ru.acceleration.store.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.user.UserRequestDto;
import ru.acceleration.store.dto.user.UserResponseDto;
import ru.acceleration.store.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> postUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("POST: /user");
        return ResponseEntity.status(201).body(userService.postUser(userRequestDto));
    }
}
