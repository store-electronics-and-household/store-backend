package ru.acceleration.store.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.dto.user.UserRequestDto;
import ru.acceleration.store.dto.user.UserRequestPatchDto;
import ru.acceleration.store.dto.user.UserResponseCheckDto;
import ru.acceleration.store.dto.user.UserResponseDto;
import ru.acceleration.store.mapper.UserMapper;
import ru.acceleration.store.model.User;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.service.UserInfoService;
import ru.acceleration.store.service.user.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user")
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "https://cyberplace.online", "http://cyberplace.online", "http://45.12.236.120"})
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final UserInfoService userInfoService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Добавление имени, фамилии, номера телефона к аккаунту", description = "Для авторизованного пользователя")
    public UserResponseDto create(@RequestBody @Valid UserRequestDto userRequestDto, Principal principal) {
        User user = userMapper.toUser(userRequestDto);
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        userService.create(user, userInfo);
        return userMapper.toUserResponseDto(user);
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Изменение имени, фамилии, номера телефона к аккаунту", description = "Для авторизованного пользователя")
    public UserResponseDto patch(@RequestBody @Valid UserRequestPatchDto userRequestPatchDto, Principal principal) {
        User user = userMapper.toUserFromPatch(userRequestPatchDto);
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        userService.patch(user, userInfo);
        return userMapper.toUserResponseDto(user);
    }

    @DeleteMapping()
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Удаление пользователя. Аккаунт с почтой и паролем сохраняется", description = "Для авторизованного пользователя")
    public void delete(Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        userService.delete(userInfo);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Получение информации пользователя (имя,фамилия, телефон, адреса)", description = "Для авторизованного пользователя")
    public UserResponseDto get(Principal principal) {
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        User user = userService.getUser(userInfo);
        return userMapper.toUserResponseDto(user);
    }

    @Operation(summary = "Проверка токена", description = "Для авторизованного пользователя")
    @GetMapping("/check")
    public UserResponseCheckDto checkToken(Principal principal) {
        log.info("UserController checkToken principal={}", principal);
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        User user = userService.getUser(userInfo);
        return UserResponseCheckDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .email(userInfo.getEmail())
                .build();
    }
}
