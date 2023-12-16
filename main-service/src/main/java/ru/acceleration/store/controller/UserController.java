package ru.acceleration.store.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://cyberplace.online", "http://cyberplace.online", "http://45.12.236.120"})
public class UserController {

    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<UserResponseDto> postUser(@Valid @RequestBody UserRequestDto userRequestDto) {
//        log.info("POST: /user");
//        return ResponseEntity.status(201).body(userService.postUser(userRequestDto));
//    }
}
