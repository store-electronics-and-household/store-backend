package ru.acceleration.store.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.acceleration.store.exceptions.BadRequestException;
import ru.acceleration.store.security.dto.AuthRequest;
import ru.acceleration.store.security.dto.AuthResponse;
import ru.acceleration.store.security.dto.UserInfoRequestDto;
import ru.acceleration.store.security.dto.UserInfoResponseDto;
import ru.acceleration.store.security.mapper.UserInfoMapper;
import ru.acceleration.store.security.model.UserInfo;
import ru.acceleration.store.security.service.JwtService;
import ru.acceleration.store.security.service.UserInfoService;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://cyberplace.online", "http://cyberplace.online", "http://45.12.236.120"})
public class UserInfoController {

    private final UserInfoService userInfoService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserInfoMapper userInfoMapper;

    @Operation(summary = "Добавление пользователя", description = "Доступ для всех")
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoResponseDto addNewUser(@RequestBody @Valid @NotNull UserInfoRequestDto userInfoRequestDto) {
        UserInfo userInfo = userInfoMapper.userRequestDtoToUserInfo(userInfoRequestDto);
        userInfo.setRoles("ROLE_USER");
        userInfoService.addUser(userInfo);
        return userInfoMapper.userInfoToUserResponseDto(userInfo);
    }

    @Operation(summary = "Вход пользователя", description = "Доступ для всех")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse authenticateAndGetToken(@RequestBody @Valid @NotNull AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserInfo userInfo = userInfoService.getUserInfo(authRequest.getEmail());
            if (userInfo.getEnabled()) {
                return AuthResponse.builder()
                        .token(jwtService.generateToken(authRequest.getEmail()))
                        .role(userInfo.getRoles())
                        .build();
            } else {
                throw new BadRequestException("Аккаунт с почтой " + userInfo.getEmail() + " был удален");
            }
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @Operation(summary = "Изменение пароля", description = "Доступ для всех")
    @PatchMapping("/change")
    public UserInfoResponseDto changePassword(@RequestBody @Valid @NotNull UserInfoRequestDto userInfoRequestDto) {
        UserInfo userInfo = userInfoMapper.userRequestDtoToUserInfo(userInfoRequestDto);
        userInfoService.changePassword(userInfo);
        return userInfoMapper.userInfoToUserResponseDto(userInfo);
    }

    @Operation(summary = "Проверка почты в базе данных", description = "Доступ для всех")
    @GetMapping("/check")
    public boolean checkEmail(@RequestParam String email) {
        userInfoService.loadUserByUsername(email);
        return true;
    }

    @Operation(summary = "Удаление аккаунта. Данные остаются в базе данных. Токен должен уничтожиться на фронте", description = "Для авторизованного пользователя")
    @DeleteMapping("/delete")
    public void deleteAccount(Principal principal) {
        userInfoService.deleteAccount(principal);
    }
}
